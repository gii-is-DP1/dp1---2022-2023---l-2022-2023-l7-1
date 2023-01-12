package org.springframework.samples.kingdommaps.casilla;

import java.util.ArrayList;
import java.util.List;

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
        return repo.findById(id).orElse(null);
    }

    public List<Casilla> getCasillasAdyacentes(Integer casillaId){
        Casilla casilla = repo.findById(casillaId).orElse(null);
        if (casilla != null) {
            return casilla.getAdyacencia();
        }
        return new ArrayList<>();
    }

    
}
