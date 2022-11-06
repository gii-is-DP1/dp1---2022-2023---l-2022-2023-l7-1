package org.springframework.samples.petclinic.poderes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoderesRepository extends CrudRepository<Poderes, Integer>{

    List<Poderes> findAll();
    
}
