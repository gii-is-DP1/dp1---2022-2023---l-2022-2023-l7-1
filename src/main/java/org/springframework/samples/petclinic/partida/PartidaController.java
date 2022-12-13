package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionForm;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.casilla.CasillaService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
@RequestMapping("/partida")
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";
    private static final String VIEW_WELCOME = "welcome";
    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";
    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final String VIEW_DIBUJAR = "partidas/dibujar";
    private static int[] dados3 =lanzamiento3();
    private static int[] dados2 =lanzamiento2();


    private PartidaService partidaService;
    private UserService userService;
    private TurnoService turnoService;
    private AccionService accionService;
    

    @Autowired
    public PartidaController(PartidaService service, UserService userService, TurnoService turnoService, AccionService accionService) {
        this.partidaService=service;
        this.userService = userService;
        this.turnoService = turnoService;
        this.accionService = accionService;
        
    }

    @Transactional
	@GetMapping(value = "/crearPartida")
	public ModelAndView creacionPartida(){
		ModelAndView res = new ModelAndView(VIEW_CREAR_PARTIDA);
		return res;
	}

    @Transactional
	@GetMapping(value = "/crearPartidaSolitaria")// tenenmos que coger el usuario que esté con la sesión iniciada
	public String getpartidaSolitaria(){
        User fran = userService.getUserById("aitroddue");
		List<Integer> x = this.partidaService.crearPartidaSolitario(fran);
        ModelAndView res = new ModelAndView("partidas/partida"); 
		return "redirect:/partida/eligeTerritorio3/"+x.get(0)+"/"+x.get(1);
	}

    static int[] lanzamiento2() {
 
		int[] resultados = new int[3]; //Se tiran tres dados
 
		resultados[0] = (int)(Math.random()*6 + 1); //Primer dado
		resultados[1] = (int)(Math.random()*6 + 1); //Segundo dado
		resultados[2] = (int)(Math.random()*6 + 1); //Tercer dado
 
		return resultados;
	}

    static int[] lanzamiento3() {
 
		int[] resultados = new int[3]; //Se tiran tres dados
 
		resultados[0] = (int)(Math.random()*6 + 1); //Primer dado
		resultados[1] = (int)(Math.random()*6 + 1); //Segundo dado
		resultados[2] = (int)(Math.random()*6 + 1); //Tercer dado
 
		return resultados;
	}

    @Transactional
    @GetMapping(value = "eligeTerritorio3/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio3(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        Turno turno = turnoService.getTurnoById(idturno);
        
        res.addObject("dados", dados3);
        res.addObject("turno", turno);
        res.addObject("territorios", listaTerritorios);
        
        
        return res;
    }

    @Transactional
    @PostMapping(value = "eligeTerritorio3/{idpartida}/{idturno}")
    public String eligeTerritorio3Post(@Valid Turno turno, BindingResult result, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno,
     Map<String, Object> model){
        if (result.hasErrors()) {
			return VIEW_ELIGE_TERRITORIO;
		}
		else {
           
                Turno turnoToBeUpdated = turnoService.getTurnoById(idturno);
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");
                turnoService.saveTurno(turnoToBeUpdated);
                Accion ac = new Accion();
                ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
                ac.setTurno(turnoToBeUpdated);
                accionService.save(ac);
           return "redirect:/partida/dibujar/"+idpartida+"/"+idturno+"/"+ac.getId();
		}
        
    }

    @Transactional  
    @GetMapping(value = "dibujar/{idpartida}/{idturno}/{idaccion}")
    public ModelAndView dibujar(Accion accion, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion) {
        ModelAndView res = new ModelAndView(VIEW_DIBUJAR);
        List<Accion> acciones =accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId());    
        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        Set<Integer> casillas = partidaService.casillasPorDibujar(partidaService.getPartidaById(idpartida).getTableros().get(0).getId(), idpartida);
        res.addObject("casillas", casillas);
        return res;
    }

    @Transactional  
    @PostMapping(value = "dibujar/{idpartida}/{idturno}/{idaccion}")
    public String dibujarPost(Accion accion,@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion
    , Map<String, Object> model){
        Turno turno = turnoService.getTurnoById(idturno);
        if(turno.getNumTerritoriosJ1()>1){
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            Accion ac = new Accion();
            ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
            ac.setTurno(turnoService.getTurnoById(idturno));
            accionService.save(ac); 
            turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
            turnoService.saveTurno(turno);
            model.put("action", ac);
            return "redirect:/partida/dibujar/"+idpartida+"/"+idturno+"/"+ac.getId();
        }else{
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            return VIEW_WELCOME;
        }
        
    }


    @Transactional  
    @GetMapping(value = "eligeTerritorio2/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio2(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        Turno turno = turnoService.getTurnoById(idturno);
        
        res.addObject("dados", dados2);
        res.addObject("turno", turno);
        res.addObject("territorios", listaTerritorios);
        
        
        return res;
    }
  
}
