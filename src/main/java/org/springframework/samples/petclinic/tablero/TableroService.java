package org.springframework.samples.petclinic.tablero;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.partida.Partida;
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

    public Tablero getTableroActiveByUser(User usuario) {
        return tableroRepository.getTableroActiveByUser(usuario);
    }

    public List<Tablero> getTablerosByUser(User usuario){
        return tableroRepository.getTablerosByUser(usuario);
    }

    public void delete(Tablero tablero) {
        tableroRepository.deleteById(tablero.getId());
    }

    public List<Tablero> getTablerosByPartida(Partida partida) {
        return tableroRepository.getTablerosByPartida(partida);
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
            if(maxPuntos.equals(tablero.getPuntos())){
                numPartidasGanadas +=1;
            }
        }
        return numPartidasGanadas;
    }

    public Integer getPuntosTotalesPorUsuario(User user){
        if(tableroRepository.getPuntosTotales(user) == null){
            return 0;
        }
        return tableroRepository.getPuntosTotales(user);
    }

    public Integer getPuntosMax(User user){
        if(tableroRepository.getPuntosMax(user)== null){
            return 0;
        }
        return tableroRepository.getPuntosMax(user);
    }

    public Integer getPuntosTotales(){
        return tableroRepository.findPuntosTotales();
    }

    public Integer getNumPartidasTotales(){
        return tableroRepository.findPartidasTotales();
    }

    public Tablero getTableroById(Integer idTablero) {
        return tableroRepository.findById(idTablero).get();
    }
}
