package org.springframework.samples.petclinic.tablero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
    
    TableroRepository tableroRepository;

    @Autowired
	public TableroService(TableroRepository tableroRepository) {
		this.tableroRepository = tableroRepository;
	}
    
    public void saveTablero(Tablero tablero){
        tableroRepository.save(tablero);
    }
}
