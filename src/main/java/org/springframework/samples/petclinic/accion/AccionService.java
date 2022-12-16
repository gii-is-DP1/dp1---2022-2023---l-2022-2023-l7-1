package org.springframework.samples.petclinic.accion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class AccionService {

    private AccionRepository accionRepository;

    @Autowired
    public AccionService(AccionRepository accionRepository) {
        this.accionRepository = accionRepository;
    }

    @Transactional
    public List<Accion> getIdAcciones(Integer partida, Integer tablero){
        List<Accion> list = new ArrayList<>();
        list = accionRepository.findByTablero(partida,tablero);
        
        
        return list;
    }

    @Transactional
    public Accion getAccionById(Integer id){
        return accionRepository.findById(id).get();
    }
    
    @Transactional
    public void save(Accion accion){
        accionRepository.save(accion);
    }

    public List<Accion> getAccionesByTablero( Tablero tablero) {
        return accionRepository.findByJugadoryTablero(tablero);
    }

    
}
