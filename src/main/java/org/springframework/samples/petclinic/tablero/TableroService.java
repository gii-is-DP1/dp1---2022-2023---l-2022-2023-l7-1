package org.springframework.samples.petclinic.tablero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
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

    public List<User> getActivePlayers() {
        return tableroRepository.getUsersWithActiveMatches();
    }

    public Tablero getTableroByUser(User usuario) {
        return tableroRepository.getTableroByUser(usuario);
    }
    
}
