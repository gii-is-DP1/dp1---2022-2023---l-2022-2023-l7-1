package org.springframework.samples.petclinic.accion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccionController {

    private AccionService accionService;

    @Autowired
    public AccionController(AccionService accionService){
        this.accionService = accionService;
    }

    @GetMapping(value = "/prueba")
    public ModelAndView showAcciones(Integer idPartida, Integer idJugador){
        ModelAndView mav = new ModelAndView("acciones/acciones");
        mav.addObject(this.accionService.getIdAcciones(idPartida, idJugador));
        return mav;
    }
    
}
