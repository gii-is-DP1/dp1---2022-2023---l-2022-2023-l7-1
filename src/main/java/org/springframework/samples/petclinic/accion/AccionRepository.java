package org.springframework.samples.petclinic.accion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionRepository extends CrudRepository<Accion, Integer>{

    @Query("SELECT u FROM Accion u JOIN Turno t ON u.turno = t.id WHERE t.tablero.id = ?1")
    public List<Accion> findByTablero(Integer tablero);
    
}
