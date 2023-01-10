package org.springframework.samples.petclinic.logros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogroService {
    LogroRepository repo;

    @Autowired
    public LogroService(LogroRepository repo){
        this.repo=repo;
    }

    public List<Logro> getLogros(){
        return repo.findAll();
    }

    public Logro getById(int id){
        return repo.findById(id).orElse(null);
    }

    public void deleteLogroById(int id){
        repo.deleteById(id);
    }

    public void save(Logro logro){
        repo.save(logro);
    }


}
