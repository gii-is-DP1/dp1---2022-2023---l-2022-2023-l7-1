package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
