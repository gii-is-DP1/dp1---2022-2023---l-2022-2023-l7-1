package org.springframework.samples.petclinic.casilla;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasillaService {
    private CasillaRepository repo;

    @Autowired
    public CasillaService(CasillaRepository casillaRepository){
        repo = casillaRepository;
    }

    public List<Casilla> getTodasCasillas(){
        return repo.findTodasCasillas(); 
    }
    public Casilla getCasillaById(Integer id){
        return repo.findById(id).get();
    }

    public List<Casilla> getCasillasAdyacentes(Integer casillaId){
        return repo.findById(casillaId).get().getAdyacencia();
    }

    
}
