package org.springframework.samples.petclinic.partida;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partida")
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";
    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";
    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);

    private PartidaService partidaService;
    private UserService userService;

    @Autowired
    public PartidaController(PartidaService service, UserService userService) {
        this.partidaService=service;
        this.userService = userService;
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

    @Transactional  
    @GetMapping(value = "eligeTerritorio/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){

        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        res.addObject("territorios", listaTerritorios);
        return res;
    }

    @Transactional  
    @PostMapping(value = "eligeTerritorio/{idpartida}/{idturno}")
    public String eligeTerritorioPost(BindingResult result, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        if (result.hasErrors()) {
			return VIEW_ELIGE_TERRITORIO;
		}
		else {
           try{
                this.partidaService.eligeTerritorio();
           }catch(Error er){

           }
			return "redirect:/";
		}
    }

   /*  @Transactional
	@PostMapping(value = "/partida")
	public String  partidaSolitaria(){
        
        //ModelAndView res = new ModelAndView("welcome");
		return "redirect:/welcome";
	}*/

}
