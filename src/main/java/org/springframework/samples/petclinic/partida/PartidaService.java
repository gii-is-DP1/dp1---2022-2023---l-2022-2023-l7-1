package org.springframework.samples.petclinic.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    
   PartidaRepository repo;
   
   @Autowired
   public PartidaService(PartidaRepository repo) {
    this.repo=repo;
   }
}
