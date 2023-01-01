package org.springframework.samples.petclinic.logros;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer> {
    List<Logro> findAll();
}
