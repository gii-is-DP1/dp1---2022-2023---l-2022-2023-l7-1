package org.springframework.samples.petclinic.turnos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService {
    TurnoRepository TurnoRepository;

    @Autowired
	public TurnoService(TurnoRepository TurnoRepository) {
		this.TurnoRepository = TurnoRepository;
	}
    
    public void saveTurno(Turno Turno){
        TurnoRepository.save(Turno);
    }

    public Turno getTurnoById(Integer id){
        return TurnoRepository.findById(id).orElse(null);
    }

    public List<Turno> getTurnosByPartida(Integer idpartida) {
        return TurnoRepository.getTurnosByPartida(idpartida);
    }

    public void delete(Turno t) {
        TurnoRepository.deleteById(t.getId());
    }
}
