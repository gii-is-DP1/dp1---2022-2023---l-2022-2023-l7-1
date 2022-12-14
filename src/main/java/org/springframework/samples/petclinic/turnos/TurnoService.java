package org.springframework.samples.petclinic.turnos;

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
        return TurnoRepository.findById(id).get();
    }
}
