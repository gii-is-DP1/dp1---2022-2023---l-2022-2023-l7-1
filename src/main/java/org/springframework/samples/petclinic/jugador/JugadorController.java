package org.springframework.samples.petclinic.jugador;

import org.springframework.beans.factory.annotation.Autowired;

public class JugadorController{

    private JugadorService service;

    @Autowired
    public JugadorController(JugadorService service) {
        this.service=service;
    }
}