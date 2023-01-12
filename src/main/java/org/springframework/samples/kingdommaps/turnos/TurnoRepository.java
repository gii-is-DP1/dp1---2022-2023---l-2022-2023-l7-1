package org.springframework.samples.kingdommaps.turnos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Integer>{

    @Query("SELECT t FROM Turno t WHERE t.partida.id = ?1")
    List<Turno> getTurnosByPartida(Integer idPartida);
    
}
