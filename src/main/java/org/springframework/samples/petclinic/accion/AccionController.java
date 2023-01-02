package org.springframework.samples.petclinic.accion;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

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

    @GetMapping(value = "/p")
    public ModelAndView showAcciones(Integer idPartida, Integer idJugador,HttpServletResponse response ){
       // response.addHeader("Refresh", "1");
        ModelAndView mav = new ModelAndView("tablero/tablero");
        //mav.addObject("now",new Date());
        mav.addObject("acciones",this.accionService.getAccionesByTablero(1));
        return mav;
    }
    
}
