package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final List<Integer> poder = List.of(0,1,-1);
    
    private static final List<Integer> dados = new ArrayList<>();

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
		return "redirect:/partida/Multijugador/espera/"+x.get(0)+"/"+x.get(1);
	}

    //-------------------------------------------------------------------------
    // Elegir Territorio ------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @GetMapping(value = "/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno,
                                        HttpSession session) {
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
        int[] dado = PartidaController.lanzamiento(partida.getTableros().size()+1);
        for(Integer i=0; i<dado.length; i++){
            dados.add(dado[i]);
        }
        session.setAttribute("dados", dado);
        res.addObject("dados", dado);

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

            //Acaba la partida dependiendo de los usos de los territorios, cambiar 
            Integer control = partidaService.actualizarUso(idPartida, turnoToBeUpdated, listaTerritorios);
            if(control <0){
                res.setViewName("redirect:/partida/resultados/"+idPartida);
                return res;
            }

            //Dirige a la vista dibujar
            Accion ac = new Accion();
            turnoToBeUpdated.setPartida(partidaService.getPartidaById(idPartida));
            turnoService.saveTurno(turnoToBeUpdated);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            ac.setTurno(turnoToBeUpdated);
            accionService.save(ac);
            User user = userService.getUserById(principal.getName());
            user.setEstado(false);
            userService.save(user);
            res.setViewName("redirect:/partida/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas+"/1"); //cambiar vista
            if(principal != null){
                res.addObject("username", principal.getName());
            }
			return res;
        }
    }
    
}