package org.springframework.samples.petclinic.accion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionRepository extends CrudRepository<Accion, Integer>{

    @Query("SELECT u FROM Accion u JOIN Tablero t ON u.tablero = t.id WHERE t.partida.id = ?1 AND t.id = ?2")
    public List<Accion> findByTablero(Integer partida, Integer tablero);

    @Query("SELECT a FROM Accion a WHERE a.tablero = ?1")
    public List<Accion> findByJugadoryTablero(Tablero tablero);
    
}
