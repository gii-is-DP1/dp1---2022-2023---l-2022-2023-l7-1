package org.springframework.samples.petclinic.partida;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";
    private static final String VIEW_AMIGOS = "partidas/lobby";

    
    private PartidaService service;

    @Autowired
    public PartidaController(PartidaService service) {
        this.service=service;
    }

    @Transactional
	@GetMapping(value = "/{username}/crearPartida")
	public ModelAndView creacionPartida(@PathVariable("username") String username){
		ModelAndView mav = new ModelAndView(VIEW_CREAR_PARTIDA);
		mav.addObject("username", username);
		return mav;
	}

    @Transactional
	@GetMapping(value = "/{username}/lobby")
	public ModelAndView mostrarAmigos(@PathVariable("username") String username){
		ModelAndView mav = new ModelAndView(VIEW_AMIGOS);
		mav.addObject("username", username);
		return mav;
	}
    
}
