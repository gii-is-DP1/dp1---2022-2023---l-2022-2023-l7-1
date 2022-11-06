package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface JugadorRepository extends CrudRepository<Jugador,Integer>{

    List<Jugador> findAll();
    Optional<Jugador> findById(int id);
    
}
