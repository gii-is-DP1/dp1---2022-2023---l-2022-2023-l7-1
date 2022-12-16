package org.springframework.samples.petclinic.tablero;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroRepository extends CrudRepository<Tablero, Integer>{

    @Query("SELECT t.user FROM Tablero t WHERE t.partidaEnCurso = true")
    List<User> getUsersWithActiveMatches();

    @Query("SELECT t FROM Tablero t WHERE t.user = ?1 AND t.partidaEnCurso = true")
    Tablero getTableroByUser(User user);
    
}
