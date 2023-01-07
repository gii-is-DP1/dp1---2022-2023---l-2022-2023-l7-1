package org.springframework.samples.petclinic.tablero;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroRepository extends CrudRepository<Tablero, Integer>{

    @Query("SELECT t.user FROM Tablero t WHERE t.partidaEnCurso = true")
    List<User> getUsersWithActiveMatches();

    @Query("SELECT t FROM Tablero t WHERE t.user = ?1 AND t.partidaEnCurso = true")
    Tablero getTableroActiveByUser(User user);

    @Query("SELECT t FROM Tablero t WHERE t.user = ?1")
    List<Tablero> getTablerosByUser(User user);

    @Query("SELECT t FROM Tablero t WHERE t.partida = ?1")
    List<Tablero> getTablerosByPartida(Partida partida);

    List<Tablero> findAll();

    @Query("SELECT user FROM User user")
    Page<Tablero> getAll(Pageable pageable);
    
    @Query("SELECT COUNT(tablero) FROM Tablero tablero WHERE tablero.user = ?1")
    Integer getNumPartidas(User user);

    @Query("SELECT SUM(tablero.puntos) FROM Tablero tablero WHERE tablero.user = ?1")
    Integer getPuntosTotales(User user);

    @Query("SELECT MAX(tablero.puntos) FROM Tablero tablero WHERE tablero.user = ?1")
    Integer getPuntosMax(User user);

    @Query("SELECT SUM(tablero.puntos) FROM Tablero tablero")
    Integer findPuntosTotales();

    @Query("SELECT MAX(tablero.partida.id) FROM Tablero tablero")
    Integer findPartidasTotales();
}
