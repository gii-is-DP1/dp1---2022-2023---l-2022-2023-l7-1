package org.springframework.samples.petclinic.turnos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Integer>{

    @Query("SELECT t FROM Turno t WHERE t.tablero.id = ?1")
    List<Turno> getTurnosByTablero(Integer idTablero);
    
}
