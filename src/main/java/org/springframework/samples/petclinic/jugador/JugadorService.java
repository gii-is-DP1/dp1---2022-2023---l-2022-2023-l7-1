package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class JugadorService {
    
    JugadorRepository repo;

    @Autowired
    public JugadorService(JugadorRepository repo) {
        this.repo = repo;
    }

    public List<Jugador> getAll(){
        return repo.findAll();
    }
}
