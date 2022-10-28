package org.springframework.samples.kingdomMaps.usuario;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioController{

    private UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service=service;
    }
}