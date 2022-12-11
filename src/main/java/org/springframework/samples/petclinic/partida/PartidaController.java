package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partida")
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";
    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";
    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final String VIEW_DIBUJAR = "partidas/dibujar";
    private static int[] dados =lanzamiento();


    private PartidaService partidaService;
    private UserService userService;
    private TurnoService turnoService;
    private AccionService accionService;
    private CasillaService casillaService;

    @Autowired
    public PartidaController(PartidaService service, UserService userService, TurnoService turnoService, AccionService accionService,CasillaService casillaService ) {
        this.partidaService=service;
        this.userService = userService;
        this.turnoService = turnoService;
        this.accionService = accionService;
        this.casillaService = casillaService;
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
		return "redirect:/partida/eligeTerritorio/"+x.get(0)+"/"+x.get(1);
	}

    static int[] lanzamiento() {
 
		int[] resultados = new int[3]; //Se tiran tres dados
 
		resultados[0] = (int)(Math.random()*6 + 1); //Primer dado
		resultados[1] = (int)(Math.random()*6 + 1); //Segundo dado
		resultados[2] = (int)(Math.random()*6 + 1); //Tercer dado
 
		return resultados;
	}

    @Transactional  
    @GetMapping(value = "eligeTerritorio/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        Turno turno = turnoService.getTurnoById(idturno);
        
        res.addObject("dados", dados);
        res.addObject("turno", turno);
        res.addObject("territorios", listaTerritorios);
        
        
        return res;
    }

    @Transactional  
    @PostMapping(value = "eligeTerritorio/{idpartida}/{idturno}")
    public String eligeTerritorioPost(@Valid Turno turno, BindingResult result, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        if (result.hasErrors()) {
			return VIEW_ELIGE_TERRITORIO;
		}
		else {
           try{
                Turno turnoToBeUpdated = turnoService.getTurnoById(idturno);
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");
                turnoService.saveTurno(turnoToBeUpdated);
           }catch(Error er){

           }
           
			return "redirect:/partida/dibujar/"+idpartida+"/"+idturno;
		}
        
    }

    @Transactional  
    @GetMapping(value = "dibujar/{idpartida}/{idturno}")
    public ModelAndView dibujar(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        ModelAndView res = new ModelAndView(VIEW_DIBUJAR);
        Turno turno = turnoService.getTurnoById(idturno);
        ArrayList<Accion> l = new ArrayList<Accion>();
        for (int i = 0; i < turno.getNumTerritoriosJ1(); i++) {
            Accion a = new Accion();
            a.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
            a.setTurno(turno);
            l.add(a);
        }
        res.addObject("acciones", l);
        if(accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId()).isEmpty()){
            res.addObject("casillas", casillaService.getTodasCasillas());
        }else{
            res.addObject("casillas", partidaService.casillasPorDibujar(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId()));
        }
        System.out.println(l.toString());
        return res;
    }

    @Transactional  
    @PostMapping(value = "dibujar/{idpartida}/{idturno}")
    public ModelAndView dibujarPost(ArrayList<Accion> acciones, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        System.out.println(acciones.toString());
        return null;
    }

   /*  @Transactional
	@PostMapping(value = "/partida")
	public String  partidaSolitaria(){
        
        //ModelAndView res = new ModelAndView("welcome");
		return "redirect:/welcome";
	}*/

}
