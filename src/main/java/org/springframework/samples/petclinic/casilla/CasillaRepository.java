package org.springframework.samples.petclinic.casilla;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasillaRepository extends CrudRepository<Casilla, Integer>{
    
    @Query("SELECT u FROM Casilla u")
    public List<Casilla> findTodasCasillas();
}
