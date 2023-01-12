package org.springframework.samples.petclinic.accion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionRepository extends CrudRepository<Accion, Integer>{

    @Query("SELECT u FROM Accion u JOIN Tablero t ON u.tablero = t.id WHERE u.turno.id = ?1 AND t.id = ?2")
    public List<Accion> findByTablero(Integer turno, Integer tablero);

    

    @Query("SELECT a FROM Accion a WHERE a.tablero.id = ?1")
    public List<Accion> findByJugadoryTablero(Integer idTablero);
    
    @Query("SELECT COUNT(a) FROM Accion a, Turno t, Tablero tab WHERE (a.turno.id = t.id AND a.tablero.id = tab.id AND tab.user = ?1 AND t.territorio = ?2)")
    public Integer findNumeroTerritoriosX(User user, Territorio territorio);

    @Query("SELECT COUNT(a) FROM Accion a, Turno t WHERE (a.turno.id = t.id AND t.territorio = ?1)")
    public Integer findNumeroTerritoriosTotales(Territorio territorio);
}
