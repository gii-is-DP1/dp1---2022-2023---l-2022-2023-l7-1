package org.springframework.samples.petclinic.accion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccionService {

    private AccionRepository accionRepository;

    @Autowired
    public AccionService(AccionRepository accionRepository) {
        this.accionRepository = accionRepository;
    }

    @Transactional
    public List<Accion> getIdAcciones(Integer idPartida, Integer idJugador){
        return accionRepository.findByTurno(idPartida, idJugador);
    }

    @Transactional
    public Accion getAccionById(Integer id){
        return accionRepository.findById(id).get();
    }
    
}
