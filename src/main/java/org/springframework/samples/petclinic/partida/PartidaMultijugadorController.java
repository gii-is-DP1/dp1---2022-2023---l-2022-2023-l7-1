package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

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
    @GetMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno) {
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
        if(dadosFijos.isEmpty()) {
            List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
            int[] dados = PartidaController.lanzamiento(tableros.size()+1);
            for(int dado: dados) {
                dadosFijos.add(dado);
            }
        }
        res.addObject("dados", dadosFijos);
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
    @PostMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorioPost(@Valid Turno turno, BindingResult result, Principal principal, Map<String, Object> model,
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
            Tablero tablero = tableroService.getTableroActiveByUser(user);
            Turno turnoToBeUpdated = turnoService.getTurnoById(idTurno);
            List<Tablero> tableros = tableroService.getTablerosByPartida(partidaService.getPartidaById(idPartida));
            Integer x= partidaService.getNumJugador(tablero,tableros);
            dadosFijos = partidaService.quitarUsoDado(x,dadosFijos,turno);
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
    
    //TO-DO
    @Transactional
    @GetMapping(value = "/partida/Multijugador/espera/dibujar/{idPartida}/{idTurno}/{idAccion}")
    public ModelAndView esperaDibujar(@PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno,
                                        @PathVariable("idAccion") Integer idAccion, Principal principal) {
        ModelAndView res = new ModelAndView();                                    
        //Comrpobar si el Jugador activo ya ha elegido el territorio
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
        User user = userService.getUserById(principal.getName());
        Tablero tablero = tableroService.getTableroActiveByUser(user);
        Integer contador = tableros.size();
        Integer contador_aux =0;
        for(Tablero t: tableros){
            if(t.getUser().getEstado()==false){
                contador_aux++;
            }
        }
        if(contador_aux ==contador){
            res.setViewName("redirect:/partida/Multijugador/dado/"+idPartida+"/"+idTurno);
            return res;
        }
        res.setViewName(VIEW_ESPERA_NUM_TERRITORIOS);     
        Turno turno = turnoService.getTurnoById(idTurno);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        
        List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId());
        List<Integer> usos = List.of(tablero.getUsos0(),tablero.getUsos1(),tablero.getUsos2(),tablero.getUsos3(),tablero.getUsos4(),tablero.getUsos5());                                            
        turno.setPartida(partida);
        turnoService.saveTurno(turno);
        res.addObject("acciones", acciones);
        res.addObject("poder1", tablero.getPoder1());
        res.addObject("usos", usos);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    
}
