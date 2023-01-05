package org.springframework.samples.petclinic.tablero;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
    
    TableroRepository tableroRepository;

    @Autowired
	public TableroService(TableroRepository tableroRepository) {
		this.tableroRepository = tableroRepository;
	}

    public Page<Tablero> getAll(Pageable pageable) {
		return tableroRepository.getAll(pageable);
	}
    
    public void saveTablero(Tablero tablero){
        tableroRepository.save(tablero);
    }

    public List<User> getActivePlayers() {
        return tableroRepository.getUsersWithActiveMatches();
    }

    public List<Tablero> getAll() {
        return tableroRepository.findAll();
    }

    public Tablero getTableroByUser(User usuario) {
        return tableroRepository.getTableroActiveByUser(usuario);
    }

    public List<Tablero> getTablerosByUser(User usuario){
        return tableroRepository.getTablerosByUser(usuario);
    }

    public void delete(Tablero tablero) {
        tableroRepository.deleteById(tablero.getId());
    }
    
    public Integer getNumPartidasJugadas(User user){
        return tableroRepository.getNumPartidas(user);
    }

    public Integer getNumPartidasGanadas(User user){
        List<Tablero> tableros = tableroRepository.getTablerosByUser(user);
        Integer numPartidasGanadas = 0;
        for(Tablero tablero: tableros){
            List<Tablero> tablerosPartida = tableroRepository.getTablerosByPartida(tablero.getPartida());
            Integer maxPuntos = tablerosPartida.stream().max(Comparator.comparing(Tablero::getPuntos)).get().getPuntos();
            if(maxPuntos == tablero.getPuntos()){
                numPartidasGanadas +=1;
            }
        }
        return numPartidasGanadas;
    }

    public Integer getPuntosTotales(User user){
        return tableroRepository.getPuntosTotales(user);
    }

    public Integer getPuntosMax(User user){
        return tableroRepository.getPuntosMax(user);
    }
}
