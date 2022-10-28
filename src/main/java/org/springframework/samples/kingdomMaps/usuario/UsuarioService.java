package org.springframework.samples.kingdomMaps.usuario;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {
    
    UsuarioRepository repo;

    @Autowired
    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }
}
