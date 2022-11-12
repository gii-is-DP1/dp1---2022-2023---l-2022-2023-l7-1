package org.springframework.samples.petclinic.partida;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";

    
    private PartidaService service;

    @Autowired
    public PartidaController(PartidaService service) {
        this.service=service;
    }

    @Transactional
	@GetMapping(value = "/crearPartida")
	public ModelAndView creacionPartida(){
		ModelAndView res = new ModelAndView(VIEW_CREAR_PARTIDA);
		return res;
	}
}
