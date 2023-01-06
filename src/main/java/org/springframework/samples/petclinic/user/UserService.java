/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.logros.Logro;
import org.springframework.samples.petclinic.logros.LogroService;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;

	private TableroService tableroService;

	private LogroService logroService;

	private AccionService accionService;

	
	@Autowired
	public UserService(UserRepository userRepository, TableroService tableroService, LogroService logroService, AccionService accionService) {
		this.userRepository = userRepository;
		this.tableroService = tableroService;
		this.logroService = logroService;
		this.accionService = accionService;
	}

	public User getUserById(String id){
        return userRepository.findById(id).get();
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Page<User> getAll(Pageable pageable) {
		return userRepository.getAll(pageable);
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException, DuplicatedUsernameException {
		User otherUser=getUserwithUsernameDifferent(user.getUsername());
            if (StringUtils.hasLength(user.getUsername()) &&  (otherUser!= null && otherUser.getUsername()!=user.getUsername())) {            	
            	throw new DuplicatedUsernameException();
            }else {
				user.setEnabled(true);
				if (user.getMatchesPlayed()==null) {user.setMatchesPlayed(0);}
				if (user.getGamesWin()==null) {user.setGamesWin(0);}
				if (user.getMaxPoints()==null) {user.setMaxPoints(0);}
				if (user.getTotalPoints()==null) {user.setTotalPoints(0);}
				if (user.getEstado()==null) {user.setEstado(false);}
				
				userRepository.save(user);
			}
	}
	
	private User getUserwithUsernameDifferent(String username) {
        List<User> users = userRepository.findAll();
		username = username.toLowerCase();
		for (User user : users) {
			String compUsername = user.getUsername();
			compUsername = compUsername.toLowerCase();
			if (compUsername.equals(username) && user.getUsername()!=username) {
				return user;
			}
		}
		return null;
	}

	public Collection<User> findUser(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findUsers(String username) {
		List<User> users =userRepository.findAll();
		List<User> res = new ArrayList<>();
		for(User user : users){
			if(user.getUsername().contains(username)){
				res.add(user);
			}
		}
		return res;
	}

	public Optional<User> findUserOptional(String username) {
		return userRepository.findByUsernameOptional(username);
	}

	public List<User> getFriends(String username) {
		return userRepository.getFriendsOf(username);
	}

	public void deleteFriend(String username, String username2){
		userRepository.Deletefriend(username, username2);
		userRepository.Deletefriend(username2, username);
	}

	public List<Tablero> getTableroByUser(String username) {
		return tableroService.getTablerosByUser(getUserById(username));
	}

	public List<Tablero> getTableros() {
		return tableroService.getAll();
	}

	public void deleteFriends(String username){
		List<User> friends = userRepository.getFriendsOf(username);
		for( User friend: friends) {
			userRepository.Deletefriend(username, friend.getUsername());
			userRepository.Deletefriend(friend.getUsername(), username);
		}
	}

	public List<Logro> getLogrosByUser(User user) {
        List<Logro> logrosUser = new ArrayList<Logro>();
        for (Logro l : logroService.getLogros()) {
            if(l.getReqPuntos() <= tableroService.getPuntosMax(user)) {
                logrosUser.add(l);
            }
        }
        return logrosUser;
    }


    public void save(User anfitrion) {
		userRepository.save(anfitrion);
    }


	public void calculaEstadisticas(User user){
		user.setMatchesPlayed(tableroService.getNumPartidasJugadas(user));
		user.setGamesWin(tableroService.getNumPartidasGanadas(user));
		user.setWinRatio(user.getWinRatio());
		
		user.setTotalPoints(tableroService.getPuntosTotalesPorUsuario(user));
		user.setMaxPoints(tableroService.getPuntosMax(user));
		
		user.setTimesUsedTerritory1(accionService.getNumTerritorios(user, Territorio.BOSQUE));
		user.setTimesUsedTerritory2(accionService.getNumTerritorios(user, Territorio.CASTILLO));
		user.setTimesUsedTerritory3(accionService.getNumTerritorios(user, Territorio.MONTANA));
		user.setTimesUsedTerritory4(accionService.getNumTerritorios(user, Territorio.POBLADO));
		user.setTimesUsedTerritory5(accionService.getNumTerritorios(user, Territorio.PRADERA));
		user.setTimesUsedTerritory6(accionService.getNumTerritorios(user, Territorio.RIO));
	}

	public List<Integer> calculaEstadisticasGlobales(){
		Integer numPartidas = tableroService.getNumPartidasTotales();
		Integer puntosTotales = tableroService.getPuntosTotales();
		Integer vecesUsadoBosque = accionService.getNumTerritoriosTotales(Territorio.BOSQUE);
		Integer vecesUsadoCastillo = accionService.getNumTerritoriosTotales(Territorio.CASTILLO); 
		Integer vecesUsadoMontana = accionService.getNumTerritoriosTotales(Territorio.MONTANA); 
		Integer vecesUsadoPoblado = accionService.getNumTerritoriosTotales(Territorio.POBLADO); 
		Integer vecesUsadoPradera = accionService.getNumTerritoriosTotales(Territorio.PRADERA); 
		Integer vecesUsadoRio = accionService.getNumTerritoriosTotales(Territorio.RIO); 
		return List.of(numPartidas,puntosTotales,vecesUsadoBosque,vecesUsadoCastillo,vecesUsadoMontana,vecesUsadoPoblado,vecesUsadoPradera,vecesUsadoRio);
	}
}
