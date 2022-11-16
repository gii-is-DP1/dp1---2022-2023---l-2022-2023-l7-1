package org.springframework.samples.petclinic.jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JugadorController{

    private JugadorService service;

    @Autowired
    public JugadorController(JugadorService service) {
        this.service=service;
    }
}