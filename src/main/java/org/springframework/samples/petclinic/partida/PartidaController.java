package org.springframework.samples.petclinic.partida;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";

    
    private PartidaService service;
    private UserService userservice;

    @Autowired
    public PartidaController(PartidaService service, UserService userService) {
        this.service=service;
        this.userservice = userService;
    }

    @Transactional
	@GetMapping(value = "/crearPartida")
	public ModelAndView creacionPartida(){
		ModelAndView res = new ModelAndView(VIEW_CREAR_PARTIDA);
		return res;
	}

    @Transactional
	@GetMapping(value = "/prueba")
	public ModelAndView getpartidaSolitaria(){
        User fran = userservice.getUserById("aitroddue");
		this.service.crearPartidaSolitario(fran);
        ModelAndView res = new ModelAndView("partidas/partida");
		return res;
	}

   /*  @Transactional
	@PostMapping(value = "/partida")
	public String  partidaSolitaria(){
        
        //ModelAndView res = new ModelAndView("welcome");
		return "redirect:/welcome";
	}*/

}
