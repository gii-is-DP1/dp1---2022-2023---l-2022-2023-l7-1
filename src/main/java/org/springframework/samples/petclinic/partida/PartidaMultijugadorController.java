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
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
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

    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";

    private static final String VIEW_ESPERA_NUM_TERRITORIOS = "partidas/esperaTerritorio";

    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final List<Integer> poder = List.of(0,1,-1);
    
    private static List<Integer> dadosFijos = new ArrayList<Integer>();
    private PartidaService partidaService;
    private UserService userService;
    private TurnoService turnoService;
    private AccionService accionService;
    private TableroService tableroService;
    
    @Autowired
    public PartidaMultijugadorController(PartidaService service, UserService userService, TurnoService turnoService, AccionService accionService, TableroService tableroService) {
        this.partidaService=service;
        this.userService = userService;
        this.turnoService = turnoService;
        this.accionService = accionService;
        this.tableroService = tableroService;
    }

    @Transactional
    @GetMapping(value = "/partida/crearPartidaMultijugador")
	public String getPartidaMultijugador(List<User> jugadores, Principal principal){
        User usuario = userService.getUserById(principal.getName());
		List<Integer> x = this.partidaService.crearPartidaMultijugador(jugadores); //x.get(0)=idPartida, x.get(1)=idturno
        if(usuario.getEstado()){
            return "redirect:/partida/Multijugador/eligeTerritorio/"+x.get(0)+"/"+x.get(1);
        }
		return "redirect:/partida/Multijugador/espera/dado";
	}

    //-------------------------------------------------------------------------
    // Elegir Territorio ------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @SuppressWarnings("unchecked") // para que no salga aviso en el cast
    @GetMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno, HttpSession session) {
        //comprobar finalizacion partida aqui
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);     
        Turno turno = turnoService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        User user = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroActiveByUser(user);
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        turno.setPartida(partida);
        List<Integer> dadosx = new ArrayList<>();
        if(!(session.getAttribute("dados")==null)){
            dadosx = (List<Integer>) session.getAttribute("dados");
        } 
        if(dadosx.isEmpty()) {
            List<Tablero> tableros = partida.getTableros();
            int[] dados = PartidaController.lanzamiento(tableros.size()+1);
            for(int dado: dados) {
                dadosx.add(dado);
            }
        }    
        res.addObject("dados", dadosx);
        session.setAttribute("dados", dadosx);
        turnoService.saveTurno(turno);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("acciones", acciones);
        res.addObject("usos", usos);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);

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
                                        @PathVariable("numTiradas") Integer numTiradas, HttpSession session) {
        ModelAndView res = new ModelAndView();
        if (result.hasErrors()) {
            if(principal != null){
                res.addObject("username", principal.getName());
            }
            res.setViewName(VIEW_ELIGE_TERRITORIO);
			return res;
		} else {
            //Actualiza las propiedades del turno 
            User user = userService.getUserById(principal.getName());
            Tablero tablero = tableroService.getTableroActiveByUser(user);
            Turno turnoToBeUpdated = turnoService.getTurnoById(idTurno);
            List<Tablero> tableros = tableroService.getTablerosByPartida(partidaService.getPartidaById(idPartida));
            Integer x= partidaService.getNumJugador(tablero,tableros);
            List<Integer> dadosx = new ArrayList<>();
            if(!(session.getAttribute("dados")==null)){
                dadosx = (List<Integer>) session.getAttribute("dados");
            } 
            dadosFijos = partidaService.quitarUsoDado(x,dadosx,turno);
            //Acaba la partida dependiendo de los usos de los territorios, cambiar 
            Integer control = partidaService.actualizarUso(idPartida, turnoToBeUpdated, listaTerritorios, tablero);
            if(control <0){
                tablero.setPartidaEnEspera(true);
                tableroService.saveTablero(tablero);
                res.setViewName("redirect:/partida/Multijugador/espera/resultados/"+idPartida);
                return res;
            }

            //Dirige a la vista dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setPartida(partidaService.getPartidaById(idPartida));
            turnoService.saveTurno(turnoToBeUpdated);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            ac.setTurno(turnoToBeUpdated);
            accionService.save(ac);
            user.setEstado(false);
            userService.save(user);
            res.setViewName("redirect:/partida/Multijugador/espera/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas+"/1"); 
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
        User user = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroActiveByUser(user);
        Partida partida = tablero.getPartida();
        List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
        Integer contador = tableros.size();
        Integer contador_aux =0;
        for(Tablero t: tableros){
            if(t.getUser().getEstado()==false){
                contador_aux++;
            }
        }
        if(contador_aux ==contador){
            res.setViewName("redirect:/partida/Multijugador/dado/"+partida.getId()+"/"+idTurno);
            return res;
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);     
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);   
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
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
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);     
        Turno turno = turnoService.getTurnoById(idTurno);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        User user = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroActiveByUser(user);
        List<Tablero> tableros = tableroService.getTablerosByPartida(partidaService.getPartidaById(idPartida));
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        turno.setPartida(partida);
        turnoService.saveTurno(turno);
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
                                        @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno, 
                                        @PathVariable("numTiradas") Integer numTiradas) {
        ModelAndView res = new ModelAndView();
        if (result.hasErrors()) {
            if(principal != null){
                res.addObject("username", principal.getName());
            }
            res.setViewName(VIEW_ELIGE_TERRITORIO);
			return res;
		} else {
            //Actualiza las propiedades del turno 
            User user = userService.getUserById(principal.getName());
            Turno turnoToBeUpdated = turnoService.getTurnoById(idTurno);
            List<Tablero> tableros = tableroService.getTablerosByPartida(partidaService.getPartidaById(idPartida));
            Integer x=0;
            for(Integer i=0; i<tableros.size();i++){
                if(tableros.get(i).getUsos0()<0 || tableros.get(i).getUsos1()<0 || tableros.get(i).getUsos2()<0 
                || tableros.get(i).getUsos3()<0 || tableros.get(i).getUsos4()<0 || tableros.get(i).getUsos5()<0){
                    x++;
                }
            }
            if(x>0){
                Tablero tablero = tableroService.getTableroActiveByUser(user);
                tablero.setPartidaEnEspera(true);
                tableroService.saveTablero(tablero);
                res.setViewName("redirect:/partida/Multijugador/espera/resultados/"+idPartida);
                return res;
            }
            //Dirige a la vista espera dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setPartida(partidaService.getPartidaById(idPartida));
            turnoService.saveTurno(turnoToBeUpdated);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            ac.setTurno(turnoToBeUpdated);
            accionService.save(ac);
            user.setEstado(false);
            userService.save(user);
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
        Turno turno = turnoService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
        if((tableros.size()==2 && turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0) ||
        (tableros.size()==3 && turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0  && turno.getNumTerritoriosJ3()>0) ||
        (tableros.size()==4 && turno.getNumTerritoriosJ1()>0 && turno.getNumTerritoriosJ2()>0  && turno.getNumTerritoriosJ3()>0
         && turno.getNumTerritoriosJ4()>0) ){
            res.setViewName("redirect:/partida/Multijugador/dibujar/"+idPartida+"/"+idTurno+"/"+idAccion +"/1");
            return res;
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);   
        User user = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroActiveByUser(user);  
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);   
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
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
        User user = userService.getUserById(principal.getName());
        Integer idTablero = tableroService.getTableroActiveByUser(user).getId();
        List<Accion> acciones = accionService.getAccionesByTablero(idTablero);
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
        res.setViewName("partidas/dibujar");  
        Tablero tablero = tableroService.getTableroActiveByUser(user);
        Turno turno = new Turno();
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Integer porDibujar = turnoService.getTurnoById(idTurno).getNumTerritoriosJ1();
        res.addObject("porDibujar", porDibujar);
        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        res.addObject("casillas", casillas);
        res.addObject("tablero", tablero);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("poder", poder);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
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
        Turno turno = turnoService.getTurnoById(idTurno);
        Accion accionToBeUpdated = accionService.getAccionById(idAccion);
        BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
        accionService.save(accionToBeUpdated);
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        turno.setPartida(partidaService.getPartidaById(idPartida));

        //Controlamos si hemos dibujado una casilla de poder y dependiendo del poder actuamos de una manera u otra
        partidaService.actualizarPoderes(accion, tablero, idPartida);
        //Esta parte actualiza el numero de territorios a dibujar y la cantidad de poderes que te quedan en el tablero PODER1
        List<Tablero> tableros = tableroService.getTablerosByPartida(partidaService.getPartidaById(idPartida));
        Integer numJugador= partidaService.getNumJugador(tablero,tableros);
        partidaService.actualizarUso1(turnoPost, turno, tablero, numJugador,accionToBeUpdated);  

        //Si quedan territorios por dibujar nos dirige al Get de dibujar, en caso contrario nos lleva a elegirTerritorio
        //TO-DO
       
        if(turno.getNumTerritoriosJ1()>0){
            
            Accion ac = new Accion();
            ac.setTablero(tablero);
            ac.setTurno(turno);
            model.put("action", ac);
            accionService.save(ac); 
            
            turnoService.saveTurno(turno);
            tableroService.saveTablero(tablero);

            res.setViewName("redirect:/partida/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas+"/"+0);
            return res;
        } else{
            Turno t = new Turno();
            turnoService.saveTurno(t);
            model.put("turno", t);
            if (numTiradas == 2) {
                res.setViewName("redirect:/partida/eligeTerritorio/"+idPartida+"/"+t.getId()+"/3");
            } else {
                res.setViewName("redirect:/partida/eligeTerritorio/"+idPartida+"/"+t.getId()+"/2");
            }
            return res;  
        }
    }

    
}
