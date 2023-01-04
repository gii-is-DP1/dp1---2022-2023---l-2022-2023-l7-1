package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartidaController {

    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";

    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final List<Integer> poder = List.of(0,1,-1);

    private PartidaService partidaService;
    private UserService userService;
    private TurnoService turnoService;
    private AccionService accionService;
    private TableroService tableroService;
    
    @Autowired
    public PartidaController(PartidaService service, UserService userService, TurnoService turnoService, AccionService accionService, TableroService tableroService) {
        this.partidaService=service;
        this.userService = userService;
        this.turnoService = turnoService;
        this.accionService = accionService;
        this.tableroService = tableroService;
    }

    //-------------------------------------------------------------------------
    // Mostrar Partida --------------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
    @GetMapping("/partidas")
	public ModelAndView showPartidas(Principal principal) {
        List<Tablero> tableros = userService.getTableros();
		ModelAndView result = new ModelAndView("users/partida");
		result.addObject("tablero", tableros);
        if(principal != null){
			result.addObject("username", principal.getName());
		}
		return result;
	}

    //-------------------------------------------------------------------------
    // PartidaAsociadaJugador -------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
    @GetMapping(value = "/partidas/{username}")
	public ModelAndView showPartidasJugador(@PathVariable("username") String username, Principal principal) {
		List<Tablero> tableros = userService.getTableroByUser(username);
		ModelAndView res = new ModelAndView("users/partida");
		res.addObject("tablero", tableros);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
		return res;
	}

    //-------------------------------------------------------------------------
    // CrearPartida -----------------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
	@GetMapping(value = "/partida/crearPartida")
	public ModelAndView creacionPartida(Principal principal){
        User usuario = userService.getUserById(principal.getName());
        List<User> usersActive = tableroService.getActivePlayers();
        ModelAndView res;
        if (usersActive.contains(usuario)){
            res = new ModelAndView("partidas/continuarCancelarPartida");
        } else{
            res = new ModelAndView("partidas/crearPartida");
        }
        if(principal != null){
            res.addObject("username", principal.getName());
        }
		return res;
	}

    //-------------------------------------------------------------------------
    // ContinuarPartidaEnCurso ------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
	@GetMapping(value = "/partida/continuarPartida")
	public String continuarPartida(Principal principal){
        User usuario = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroByUser(usuario);
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Turno> turnos = turnoService.getTurnosByTablero(tablero.getId());
        Turno turno = turnos.get(turnos.size()-1);
        if(!acciones.isEmpty()){
            Accion accion = acciones.get(acciones.size()-1);
            if(turno.getId()!=accion.getTurno().getId()){
                if ((turno.getId()-turnos.get(0).getId())%2==0){
                    return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/3";
                } else {
                    return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/2";
                }
            } else{
                Integer primeraAccion= partidaService.getPrimeraAccion(acciones, turno);
                if ((turno.getId()-turnos.get(0).getId())%2==0){
                    return "redirect:/partida/dibujar/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId()+"/3/"+ primeraAccion;
                } else {
                    return "redirect:/partida/dibujar/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId()+"/2/" + primeraAccion;
                }
            }
        }
        return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/3";
    }

    //-------------------------------------------------------------------------
    // CancelarPartidaEnCurso -------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
	@GetMapping(value = "/partida/cancelarPartida")
	public String cancelarPartida(Principal principal){
        User usuario = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroByUser(usuario);
        Partida partida = partidaService.getPartidaById(tablero.getPartida().getId());
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Turno> turnos = turnoService.getTurnosByTablero(tablero.getId());
        tableroService.delete(tablero);
        for(Accion a: acciones){
            accionService.delete(a);
        }
        for(Turno t: turnos){
            turnoService.delete(t);
        }
        partidaService.delete(partida);
        return "redirect:/";
    }

    //-------------------------------------------------------------------------
    //PartidaSolitaria---------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @GetMapping(value = "/partida/crearPartidaSolitaria")
	public String getPartidaSolitaria(Principal principal){
        User usuario = userService.getUserById(principal.getName());
		List<Integer> x = this.partidaService.crearPartidaSolitario(usuario);
		return "redirect:/partida/eligeTerritorio/"+x.get(0)+"/"+x.get(1)+"/3";
	}

    // Metodo para los lanzamientos del dado
    static int[] lanzamiento(int num) {
		int[] resultados = new int[num];

        for (int i=0;i<num;i++) {
            resultados[i] = (int) (Math.random()*6 + 1);
        }		
		return resultados;
	}

    //-------------------------------------------------------------------------
    // Elegir Territorio ------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @GetMapping(value = "/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno, @PathVariable("numTiradas") Integer numTiradas,
                                        HttpSession session) {
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        
        
        Turno turno = turnoService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        Boolean eligeTerritorio;

        if(numTiradas == 2) {
            turno.setTablero(acciones.get(0).getTablero());
            int[] dados = lanzamiento(numTiradas);
            session.setAttribute("dados", dados);
            res.addObject("dados", dados);
            eligeTerritorio = false;
            
        } else {
            turno.setTablero(tablero);
            res.addObject("territorios", listaTerritorios);
            res.addObject("dados", lanzamiento(numTiradas));
            eligeTerritorio = true;
        }

        turnoService.saveTurno(turno);
        res.addObject("eligeTerritorio", eligeTerritorio);
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
    @PostMapping(value = "/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}")
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
            //Actualiza las propiedades del turno para numTiradas == 3
            Turno turnoToBeUpdated = turnoService.getTurnoById(idTurno);
            BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");

            //Actualiza las propiedades del turno para numTiradas == 2
            if (numTiradas == 2) {
                int[] dados = (int[]) session.getAttribute("dados");
                Integer territorio = dados[0]-1;

                if(dados[0] == turno.getNumTerritoriosJ1()){
                    territorio = dados[1]-1;
                }
                turnoToBeUpdated.setTerritorio(listaTerritorios.get(territorio));
                model.put("turno", turnoToBeUpdated);
            }

            //Acaba la partida dependiendo de los usos de los territorios
            Integer control = partidaService.actualizarUso(idPartida, turnoToBeUpdated, listaTerritorios);
            if(control <0){
                res.setViewName("redirect:/partida/resultados/"+idPartida);
                return res;
            }

            //Dirige a la vista dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            turnoService.saveTurno(turnoToBeUpdated);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            ac.setTurno(turnoToBeUpdated);
            accionService.save(ac);

            res.setViewName("redirect:/partida/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas+"/1");
            if(principal != null){
                res.addObject("username", principal.getName());
            }
			return res;
        }
    }

    //-------------------------------------------------------------------------
    // Dibujar Tablero --------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional  
    @GetMapping(value = "/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}")
    public ModelAndView dibujar(Accion accion, @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno,
                                 @PathVariable("idAccion") Integer idAccion, @PathVariable("numTiradas") Integer numTiradas,
                                 @PathVariable("primeraAccion") Integer primeraAccion, Principal principal){

        ModelAndView res = new ModelAndView();
        
        
        Integer idTablero = partidaService.getPartidaById(idPartida).getTableros().get(0).getId();
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
            res.setViewName("redirect:/partida/resultados/"+idPartida);
            return res;
        }
        
        //Define la vista y añade los atributos necesarios 
        res.setViewName("partidas/dibujar");
        
        
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        Turno turno = new Turno();
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);

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
    @PostMapping(value = "/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}")
    public ModelAndView dibujarPost(Turno turnoPost, Accion accion, @PathVariable("idPartida") Integer idPartida, Principal principal,
                            @PathVariable("idTurno") Integer idTurno, @PathVariable("idAccion") Integer idAccion,
                            @PathVariable("numTiradas") Integer numTiradas, @PathVariable("primeraAccion") Integer primeraAccion,
                            Map<String, Object> model){

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
        turno.setTablero(tablero);

        //Esta parte actualiza el numero de territorios a dibujar y la cantidad de poderes que te quedan en el tablero PODER1
        if(turnoPost.getNumTerritoriosJ1() == null|| turnoPost.getNumTerritoriosJ1() == 0){
            turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
            
        }else if(turnoPost.getNumTerritoriosJ1() == -1){
            turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-2);
            tablero.setPoder1(tablero.getPoder1()-1);
        }else if(turnoPost.getNumTerritoriosJ1() == 1){
            tablero.setPoder1(tablero.getPoder1()-1);
        }


        //Controlamos si hemos dibujado una casilla de poder y dependiendo del poder actuamos de una manera u otra
        if(accion.getCasilla().getPoder1()) {
            tablero.setPoder1(tablero.getPoder1()+1);
            tableroService.saveTablero(tablero);
        }

        if(accion.getCasilla().getPoder2()){
            Partida partida = partidaService.getPartidaById(idPartida);

            List<Turno> turnos = turnoService.getTurnosByTablero(tablero.getId());

            List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId()).stream().filter(x-> x.getCasilla() != null).collect(Collectors.toList());
            Integer puntosPoder2 = partidaService.calcularPoder2(acciones, turnos, partida);
            tablero.setPoder2(puntosPoder2);
            tableroService.saveTablero(tablero);
        }

        //Si quedan territorios por dibujar nos dirige al Get de dibujar, en caso contrario nos lleva a elegirTerritorio
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

    //Nos lleva a la vista tras acabar la partida y guarda todas las propiedades necesarias
    @GetMapping(value = "/partida/resultados/{idPartida}")
	public ModelAndView resultados(@PathVariable("idPartida") Integer idPartida){
        ModelAndView res = new ModelAndView("partidas/resultados");
		Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        tablero.setPartidaEnCurso(false);
        tableroService.saveTablero(tablero);

        Partida partida = partidaService.getPartidaById(idPartida);

        List<Turno> turnos = turnoService.getTurnosByTablero(tablero.getId());

        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId()).stream().filter(x-> x.getCasilla() != null).collect(Collectors.toList());

        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);

        Integer criterioA1 = partidaService.calcularPuntosCriterioA1(acciones, turnos, partida);
        Integer criterioA2 = partidaService.calcularPuntosCriterioA2(acciones, turnos, partida);
        Integer criterioB1 = partidaService.calcularPuntosCriterioB1(acciones, turnos, partida);
        Integer criterioB2 = partidaService.calcularPuntosCriterioB2(acciones, turnos, partida);

        Integer puntosTotales = criterioA1 + criterioA2 + criterioB1 + criterioB2+ tablero.getPoder2();
        
        tablero.setPuntos(puntosTotales);
        tableroService.saveTablero(tablero);
        res.addObject("criterioA1", criterioA1);
        res.addObject("criterioA2", criterioA2);
        res.addObject("criterioB1", criterioB1);
        res.addObject("criterioB2", criterioB2);
        res.addObject("poder2", tablero.getPoder2());
        res.addObject("puntosTotales", puntosTotales);
        res.addObject("acciones", acciones);
        res.addObject("criterios", criterios);
        return res;
	}

}
