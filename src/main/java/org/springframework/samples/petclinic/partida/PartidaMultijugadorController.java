package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaMultijugadorController {

    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorioMultijugador";

    private static final String VIEW_ESPERA_NUM_TERRITORIOS = "partidas/esperaTerritorio";

    private static final String VIEW_ELIGE_DADO = "partidas/elegirDado";

    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final List<Integer> poder = List.of(0,1,-1);
    
    private static final List<Integer> dadosFijos = new ArrayList<Integer>();
    private PartidaService partidaService;
    
    @Autowired
    public PartidaMultijugadorController(PartidaService service) {
        this.partidaService=service;
    }

    @Transactional
    @GetMapping(value = "/partida/crearPartidaMultijugador")
	public String getPartidaMultijugador(Principal principal){
        User usuario = partidaService.getUserById(principal);
		List<Integer> x = this.partidaService.crearPartidaMultijugador(usuario.getJugadoresAceptados()); //x.get(0)=idPartida, x.get(1)=idturno
        if(usuario.getEstado()){
            return "redirect:/partida/Multijugador/eligeTerritorio/"+x.get(0)+"/"+x.get(1);
        }
		return "redirect:/partida/Multijugador/espera/dado/"+x.get(1);
	}

    //-------------------------------------------------------------------------
    // Elegir Territorio ------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @SuppressWarnings("unchecked") // para que no salga aviso en el cast
    @GetMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno, HttpSession session) {
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);     
        Turno turno = partidaService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Tablero tablero = partidaService.getTableroActiveByUser(principal);
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        turno.setPartida(partida);
        List<Integer> dadosx = new ArrayList<>();
        if(!(session.getAttribute("dados")==null)){
            dadosx = (List<Integer>) session.getAttribute("dados");
        } 
        if(dadosx.isEmpty()) {
            tableros = partida.getTableros();
            int[] dados = PartidaController.lanzamiento(tableros.size()+1);
            for(int dado: dados) {
                dadosx.add(dado);
            }
        }    
        res.addObject("dados", dadosx);
        session.setAttribute("dados", dadosx);
        turno.setPartida(partida);
        partidaService.saveTurno(turno);
        res.addObject("territorios", listaTerritorios);
        Boolean eligeTerritorio = true;
        res.addObject("eligeTerritorio", eligeTerritorio);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("acciones", acciones);
        res.addObject("usos", usos);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        res.addObject("numJugador", numJugador);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    @Transactional
    @SuppressWarnings("unchecked") // para que no salga aviso en el cast
    @PostMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorioPost(@Valid Turno turno, BindingResult result, Principal principal, Map<String, Object> model,
                                        @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno, 
                                         HttpSession session) {
        ModelAndView res = new ModelAndView();
        if (result.hasErrors()) {
            if(principal != null){
                res.addObject("username", principal.getName());
            }
            res.setViewName(VIEW_ELIGE_TERRITORIO);
			return res;
		} else {
            //Actualiza las propiedades del turno 
            User user = partidaService.getUserById(principal);
            Tablero tablero = partidaService.getTableroActiveByUser(principal);
            Turno turnoToBeUpdated = partidaService.getTurnoById(idTurno);
            BeanUtils.copyProperties(turno, turnoToBeUpdated, "id");
            List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
            Integer x= partidaService.getNumJugador(tablero,tableros);
            List<Integer> dadosx = new ArrayList<>();
            if(!(session.getAttribute("dados")==null)){
                dadosx = (List<Integer>) session.getAttribute("dados");
            } 
            dadosx = partidaService.quitarUsoDado(x,dadosx,turno);
            if(dadosFijos.isEmpty()) dadosFijos.addAll(dadosx);
            dadosx.clear();
            session.setAttribute("dados", dadosx);
            //Acaba la partida dependiendo de los usos de los territorios, cambiar 
            Integer control = partidaService.actualizarUso(idPartida, turnoToBeUpdated, listaTerritorios, tablero);
            if(control <0){
                partidaService.saveTableroEnEspera(tablero);
                res.setViewName("redirect:/partida/Multijugador/espera/resultados/"+idPartida);
                return res;
            }

            //Dirige a la vista dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setPartida(partidaService.getPartidaById(idPartida));
            partidaService.saveTurno(turnoToBeUpdated);
            Integer numJugador= partidaService.getNumJugador(tablero,tableros);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(numJugador-1));
            ac.setTurno(turnoToBeUpdated);
            partidaService.saveAccion(ac);
            partidaService.saveUserEstadoFalse(user);
            res.setViewName("redirect:/partida/Multijugador/espera/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()); 
            if(principal != null){
                res.addObject("username", principal.getName());
            }
			return res;
        }
    }

    @Transactional
    @GetMapping(value = "/partida/Multijugador/espera/dado/{idTurno}")
    public ModelAndView esperaElegirNumTerritorios(Principal principal, @PathVariable("idTurno") Integer idTurno) {
        ModelAndView res = new ModelAndView();                                    
        //Comrpobar si el Jugador activo ya ha elegido el territorio
        Tablero tablero = partidaService.getTableroActiveByUser(principal);
        Partida partida = tablero.getPartida();
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(partida.getId());
        Integer contador = partidaService.getNumTablerosEnEsperaDado(tableros);
        Boolean partidaEnEspera = partidaService.getPartidaEnEspera(tableros);
        if(partidaEnEspera){
            partidaService.saveTableroEnEspera(tablero);
            res.setViewName("redirect:/partida/Multijugador/espera/resultados/"+partida.getId());
            return res;
        }
        if(tableros.size() ==contador){
            res.setViewName("redirect:/partida/Multijugador/dado/"+partida.getId()+"/"+idTurno);
            return res;
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);     
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);   
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("criterios", criterios);
        return res;
    }

    @Transactional
    @GetMapping(value = "/partida/Multijugador/dado/{idPartida}/{idTurno}")
    public ModelAndView ElegirNumTerritorios(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno) {
        ModelAndView res = new ModelAndView();                                    
        Partida partida = partidaService.getPartidaById(idPartida);
        res.setViewName(VIEW_ELIGE_DADO);     
        Turno turno = partidaService.getTurnoById(idTurno);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Tablero tablero = partidaService.getTableroActiveByUser(principal);
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        turno.setPartida(partida);
        partidaService.saveTurno(turno);
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        res.addObject("dados", dadosFijos);
        res.addObject("numJugador", numJugador);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    @Transactional
    @PostMapping(value = "/partida/Multijugador/dado/{idPartida}/{idTurno}")
    public ModelAndView ElegirNumTerritoriosPost(@Valid Turno turno, BindingResult result, Principal principal, Map<String, Object> model,
                                        @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno) {
        ModelAndView res = new ModelAndView();
        if (result.hasErrors()) {
            if(principal != null){
                res.addObject("username", principal.getName());
            }
            res.setViewName(VIEW_ELIGE_TERRITORIO);
			return res;
		} else {
            //Actualiza las propiedades del turno 
            User user = partidaService.getUserById(principal);
            Tablero tablero = partidaService.getTableroActiveByUser(principal);
            List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
            Integer numJugador= partidaService.getNumJugador(tablero,tableros);
            Turno turnoToBeUpdated = partidaService.getTurnoById(idTurno);
            if(numJugador==1){
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4", "territorio");
            } else if(numJugador==2){
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ1","numTerritoriosJ3","numTerritoriosJ4", "territorio");
            } else if(numJugador==3){
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ1","numTerritoriosJ4", "territorio");
            } else if(numJugador==4){
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ1", "territorio");
            }            
            //Dirige a la vista espera dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setPartida(partidaService.getPartidaById(idPartida));
            partidaService.saveTableroTurnoAccion(tablero,turnoToBeUpdated,ac);       
            partidaService.saveUserEstadoFalse(user);
            res.setViewName("redirect:/partida/Multijugador/espera/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()); 
            if(principal != null){
                res.addObject("username", principal.getName());
            }
			return res;
        }
    }
    
    @Transactional
    @GetMapping(value = "/partida/Multijugador/espera/dibujar/{idPartida}/{idTurno}/{idAccion}")
    public ModelAndView esperaDibujar(@PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno,
                                        @PathVariable("idAccion") Integer idAccion, Principal principal) {
        ModelAndView res = new ModelAndView();                                    
        //Comprobar si todos han elegido dado
        Turno turno = partidaService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(partida.getId());
        if(turno.getNumTerritoriosJ1()!=null && turno.getNumTerritoriosJ2()!=null && tableros.size()==2){
            if(turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0){
                res.setViewName("redirect:/partida/Multijugador/dibujar/"+idPartida+"/"+idTurno+"/"+idAccion +"/1");
                return res;
            }
        }else if(turno.getNumTerritoriosJ1()!=null && turno.getNumTerritoriosJ2()!=null && turno.getNumTerritoriosJ3()!=null && tableros.size()==3){
            if(turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0  && turno.getNumTerritoriosJ3()>0){
                res.setViewName("redirect:/partida/Multijugador/dibujar/"+idPartida+"/"+idTurno+"/"+idAccion +"/1");
                return res;
            }
        } else if(turno.getNumTerritoriosJ1()!=null && turno.getNumTerritoriosJ2()!=null && turno.getNumTerritoriosJ3()!=null &&
                  turno.getNumTerritoriosJ4()!=null && tableros.size()==4){
            if(turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0  && turno.getNumTerritoriosJ3()>0 && turno.getNumTerritoriosJ4()>0){
                res.setViewName("redirect:/partida/Multijugador/dibujar/"+idPartida+"/"+idTurno+"/"+idAccion +"/1");
                return res;
            }
        } 
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);   
        Tablero tablero = partidaService.getTableroActiveByUser(principal);  
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);   
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                          
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("criterios", criterios);
        return res;
    }

    @Transactional  
    @GetMapping(value = "/partida/Multijugador/dibujar/{idPartida}/{idTurno}/{idAccion}/{primeraAccion}")
    public ModelAndView dibujar(Accion accion, @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno,
                                 @PathVariable("idAccion") Integer idAccion, @PathVariable("primeraAccion") Integer primeraAccion, Principal principal){
        ModelAndView res = new ModelAndView();     
        Integer idTablero = partidaService.getTableroActiveByUser(principal).getId();
        List<Accion> acciones = partidaService.getAccionesByTablero(idTablero);
        Set<Integer> casillas = new HashSet<>();

        //Calcula las casillas disponibles a dibujar dependiendo de si es la primera accion del turno o no
        if(primeraAccion == 1){
            casillas = partidaService.casillasDisponiblesPrimeraAccion(idTablero);
        }else{
            casillas = partidaService.casillasDisponibles(idTurno, idTablero);
        } 
        //Controla si acaba la partida
        if(casillas.isEmpty()){
            res.setViewName("redirect:/partida/Multijugador/espera/resultados/"+idPartida);
            return res;
        }
        
        //Define la vista y añade los atributos necesarios 
        res.setViewName("partidas/dibujarMultijugador");  
        Tablero tablero = partidaService.getTableroActiveByUser(principal);
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        Turno turno = partidaService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Integer porDibujar=partidaService.getAccionesPorDibujar(turno, numJugador);
        res.addObject("porDibujar", porDibujar);
        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        res.addObject("casillas", casillas);
        res.addObject("tablero", tablero);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("poder", poder);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        res.addObject("numJugador", numJugador);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    @Transactional
    @PostMapping(value = "/partida/Multijugador/dibujar/{idPartida}/{idTurno}/{idAccion}/{primeraAccion}")
    public ModelAndView dibujarPost(Turno turnoPost, Accion accion, @PathVariable("idPartida") Integer idPartida, Principal principal,
                            @PathVariable("idTurno") Integer idTurno, @PathVariable("idAccion") Integer idAccion,
                            @PathVariable("primeraAccion") Integer primeraAccion, Map<String, Object> model){

        ModelAndView res = new ModelAndView();
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        //Actualiza la acción y la guarda en la BBDD
        Turno turno = partidaService.getTurnoById(idTurno);
        Accion accionToBeUpdated = partidaService.getAccionById(idAccion);
        BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
        partidaService.saveAccion(accionToBeUpdated);
        Tablero tablero = partidaService.getTableroActiveByUser(principal);
        
        //Controlamos si hemos dibujado una casilla de poder y dependiendo del poder actuamos de una manera u otra
        partidaService.actualizarPoderes(accion, tablero, idPartida);
        //Esta parte actualiza el numero de territorios a dibujar y la cantidad de poderes que te quedan en el tablero PODER1
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(idPartida);
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        partidaService.actualizarUso1(turnoPost, turno, tablero, numJugador,accionToBeUpdated);  

        //Si quedan territorios por dibujar nos dirige al Get de dibujar, en caso contrario nos lleva a elegirTerritorio
        Integer porDibujar= partidaService.getAccionesPorDibujar(turno, numJugador);
        if(porDibujar>0){
            Accion ac = new Accion();
            partidaService.saveTableroTurnoAccion(tablero,turno,ac);
            model.put("action", ac);
            res.setViewName("redirect:/partida/Multijugador/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+0);
            return res;
        } else{
            Integer ultimoJuadorActivo = partidaService.getUltimoJugadorActivo(tableros,turno); 
            if(ultimoJuadorActivo+1==numJugador){
                dadosFijos.clear();
                Turno t = new Turno();
                t.setPartida(partidaService.getPartidaById(idPartida));
                partidaService.saveTurno(t);
                model.put("turno", t);
                res.setViewName("redirect:/partida/Multijugador/espera/eligeTerritorio/"+ t.getId());      
                return res; 
            }
            res.setViewName("redirect:/partida/Multijugador/espera/espera/dado/"+idPartida);
            return res;  
        }
    }

    @Transactional
    @GetMapping(value = "/partida/Multijugador/espera/espera/dado/{idPartida}")
    public ModelAndView esperaEsperaDado(@PathVariable("idPartida") Integer idPartida, Principal principal) {
        ModelAndView res = new ModelAndView();                                    
        //Comprobar que haya jugador activo
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(partida.getId());
        for(Tablero t:tableros){
            if(t.getUser().getEstado()){
                Turno turno = partidaService.getUltimoTurno(partida);
                res.setViewName("redirect:/partida/Multijugador/espera/dado/"+turno.getId());
                return res;
            }
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);   
        Tablero tablero = partidaService.getTableroActiveByUser(principal);  
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);   
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                          
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("criterios", criterios);
        return res;
    }

    @Transactional
    @GetMapping(value = "/partida/Multijugador/espera/eligeTerritorio/{idTurno}")
    public ModelAndView esperaElegirTerritorio(@PathVariable("idTurno") Integer idTurno, Principal principal) {
        ModelAndView res = new ModelAndView();                                    
        //Comprobar que haya todos hayan acabado de dibujar
        Turno turno = partidaService.getTurnoById(idTurno);
        Partida partida = turno.getPartida();
        List<Tablero> tableros = partidaService.getTablerosByPartidaId(partida.getId());
        Tablero tablero = partidaService.getTableroActiveByUser(principal);  
        List<Accion> acciones = partidaService.getAccionesByTablero(tablero.getId());
        Turno turnoAnterior = acciones.get(acciones.size()-1).getTurno();
        if(turnoAnterior.getNumTerritoriosJ1()==0 && turnoAnterior.getNumTerritoriosJ2()==0 &&
          (tableros.size()<3 || turnoAnterior.getNumTerritoriosJ3()==0 ) &&
          (tableros.size()<4 || turnoAnterior.getNumTerritoriosJ4()==0 )){
                partidaService.saveJugadorActivo(principal);
                res.setViewName("redirect:/partida/Multijugador/eligeTerritorio/"+partida.getId()+"/"+ idTurno);
                return res;
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);   
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                          
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("criterios", criterios);
        return res;
    }
   
}
