package org.springframework.samples.petclinic.partida;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer>{

    // @Query("SELECT partida FROM Partida partida JOIN user f WHERE f.username = :username")
    // Integer findGamesPlayed(String username);
}
