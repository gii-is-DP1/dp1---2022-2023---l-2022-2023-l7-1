package org.springframework.samples.petclinic.accion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionRepository extends CrudRepository<Accion, Integer>{

    @Query("SELECT u FROM Accion u JOIN Turno t ON u.idTurno = t.id WHERE t.idJugador = ?2 AND t.idPartida = ?1")
    public List<Accion> findByTurno(Integer idPartida, Integer idJugador);
    
}
