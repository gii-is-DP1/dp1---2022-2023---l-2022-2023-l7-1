package org.springframework.samples.petclinic.tablero;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class TableroServiceTests {

    @Autowired
    protected TableroService tableroService;

    @Autowired
    protected PartidaService partidaService;

    @Autowired
    protected UserService userService;

    protected Tablero tab = new Tablero();

    protected Partida p = new Partida();
    
    @BeforeEach
    public void setTablero( ){
        
        tab.setPartida(partidaService.getPartidaById(1));
        tab.setPartidaCreada(true);
        tab.setPartidaEnCurso(true);
        tab.setPartidaEnEspera(true);
        tab.setPoder1(0);
        tab.setPoder2(0);
        tab.setPuntos(1);
        tab.setUser(userService.getUserById("owner1"));
        tab.setUsos0(0);
        tab.setUsos1(0);
        tab.setUsos2(0);
        tab.setUsos3(0);
        tab.setUsos4(0);
        tab.setUsos5(0);
        tableroService.saveTablero(tab);
    }

    @Test
    @Transactional
    public void shouldGet1ActivePlayer(){
        List<User> activePlayers = this.tableroService.getActivePlayers();
        User owner = activePlayers.get(0);
        assertThat(owner.getName()).isEqualTo("ow");
        assertThat(activePlayers.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void shouldGetTableroOwner1(){
        User owner1 = userService.getUserById("owner1");
        Tablero tab = tableroService.getTableroActiveByUser(owner1);
        assertThat(tab).isNotNull();
        assertThat(tab.getUser()).isEqualTo(owner1);
        assertThat(tab.getPartida().getId()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void shouldGetTablerosByUser(){
        User owner1 = userService.getUserById("owner1");
        List<Tablero> tableros = tableroService.getTablerosByUser(owner1);
        assertThat(tableros).isNotNull();
        assertThat(tableros.size()).isEqualTo(1);

        User fravilpae = userService.getUserById("fravilpae");
        List<Tablero> tablerosFrancis = tableroService.getTablerosByUser(fravilpae);
        assertThat(tablerosFrancis.size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldGetTablerosByPartida(){
        Partida partida = partidaService.getPartidaById(1);
        List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
        assertThat(tableros).isNotNull();
        assertThat(tableros.size()).isEqualTo(3);
        assertThat(tableros.get(0).getUser().getName()).isEqualTo("Aitor");
        assertThat(tableros.get(1).getUser().getName()).isEqualTo("Ramonr");
    }

    @Test
    @Transactional
    public void shouldGetNumPartidasJugadas(){
        
        Integer numPartidasJugadasOwner1 = tableroService.getNumPartidasJugadas(userService.getUserById("owner1"));
        assertThat(numPartidasJugadasOwner1).isEqualTo(1);

        Integer numPartidasJugadasFrancis = tableroService.getNumPartidasJugadas(userService.getUserById("fravilpae"));
        assertThat(numPartidasJugadasFrancis).isEqualTo(0);

    }

    @Test
    @Transactional
    public void shouldGetNumPartidasGanadas(){
        
        Integer numPartidasGanadasAitor = tableroService.getNumPartidasGanadas(userService.getUserById("aitroddue"));
        assertThat(numPartidasGanadasAitor).isEqualTo(1);

        Integer numPartidasJugadasFrancis = tableroService.getNumPartidasGanadas(userService.getUserById("fravilpae"));
        assertThat(numPartidasJugadasFrancis).isEqualTo(0);
        
    }

    @Test
    @Transactional
    public void shouldGetPuntosTotalesUsuario(){
        
        Integer puntosTotalesAitor = tableroService.getPuntosTotalesPorUsuario(userService.getUserById("aitroddue"));
        assertThat(puntosTotalesAitor).isEqualTo(10);
        
        Integer puntosTotalesFrancis = tableroService.getPuntosTotalesPorUsuario(userService.getUserById("fravilpae"));
        assertThat(puntosTotalesFrancis).isEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldGetPuntosMaxUsuario(){
        
        Integer puntosTotalesAitor = tableroService.getPuntosTotalesPorUsuario(userService.getUserById("aitroddue"));
        assertThat(puntosTotalesAitor).isEqualTo(10);
        
        Integer puntosTotalesFrancis = tableroService.getPuntosTotalesPorUsuario(userService.getUserById("fravilpae"));
        assertThat(puntosTotalesFrancis).isEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldGetPuntosTotales(){
        
        Integer puntosTotales = tableroService.getPuntosTotales();
        assertThat(puntosTotales).isEqualTo(11);
        
    }

    @Test
    @Transactional
    public void shouldGetPartidasTotales(){
        
        Integer partidasTotales = tableroService.getNumPartidasTotales();
        assertThat(partidasTotales).isEqualTo(1);
        
    }

    @Test
    public void shouldCheckTieneUnaPartida() {
        User user2 = new User();
        Boolean res = tableroService.tieneUnaPartida(user2);
        assertThat(res).isEqualTo(false);
        res = tableroService.tieneUnaPartida(userService.getUserById("owner1"));
        assertThat(res).isEqualTo(true);
    }

    @Test
    public void shouldGetPuntosMax() {
        Integer res = tableroService.getPuntosMax(userService.getUserById("fravilpae"));
        assertThat(res).isEqualTo(0);
        res = tableroService.getPuntosMax(userService.getUserById("owner1"));
        assertThat(res).isEqualTo(1);
    }
}