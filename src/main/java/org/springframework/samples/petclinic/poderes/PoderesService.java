package org.springframework.samples.petclinic.poderes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoderesService {

    PoderesRepository repo;

    @Autowired
    public PoderesService(PoderesRepository repo) {
        this.repo=repo;
    }
    
    public List<Poderes> getAll() {
        return repo.findAll();
    }
}
