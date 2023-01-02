package org.springframework.samples.petclinic.turnos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.tablero.Tablero;
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

    public List<Turno> getTurnosByTablero(Integer idTablero) {
        return TurnoRepository.getTurnosByTablero(idTablero);
    }
}
