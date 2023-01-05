package org.springframework.samples.petclinic.accion;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;

@Service
public class AccionService {

    private AccionRepository accionRepository;

    @Autowired
    public AccionService(AccionRepository accionRepository) {
        this.accionRepository = accionRepository;
    }

    @Transactional
    public List<Accion> getCasillasPorTurno(Integer turno, Integer tablero){
        List<Accion> list = new ArrayList<>();
        list = accionRepository.findByTablero(turno, tablero);
        
        
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

    public List<Accion> getAccionesByTablero( Integer idTablero) {
        return accionRepository.findByJugadoryTablero(idTablero);
    }

    public void delete(Accion a) {
        accionRepository.deleteById(a.getId());
    }

    public Integer getNumTerritorios(User user, Territorio territorio){
        
        return accionRepository.findNumeroTerritoriosX(user, territorio);
    }

    public Integer getNumTerritoriosTotales(Territorio territorio){
        return accionRepository.findNumeroTerritoriosTotales(territorio);
    }
    
}
