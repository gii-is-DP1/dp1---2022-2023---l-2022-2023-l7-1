package org.springframework.samples.petclinic.partida;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PartidaServiceTests {                
    
	@Autowired
	protected PartidaService partidaService;
    @Autowired
    protected TurnoService turnoService;
    @Autowired
    protected TableroService tableroService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected AccionService accionService;

    protected Partida p = new Partida();
    protected Tablero tab = new Tablero();
    protected Tablero tab2 = new Tablero();

    @BeforeEach
    public void setTablero1(){
        tab.setId(10);
        tab.setPartida(p);
        tab.setPartidaCreada(true);
        tab.setPartidaEnCurso(true);
        tab.setPartidaEnEspera(false);
        tab.setPoder1(0);
        tab.setPoder2(0);
        tab.setPuntos(0);
        tab.setUser(userService.getUserById("aitroddue"));
        tab.setUsos0(2);
        tab.setUsos1(2);
        tab.setUsos2(2);
        tab.setUsos3(2);
        tab.setUsos4(2);
        tab.setUsos5(2);
        tableroService.saveTablero(tab);
    }

    @BeforeEach
    public void setTablero2(){
        tab2.setId(11);
        tab2.setPartida(p);
        tab2.setPartidaCreada(true);
        tab2.setPartidaEnCurso(true);
        tab2.setPartidaEnEspera(false);
        tab2.setPoder1(0);
        tab2.setPoder2(0);
        tab2.setPuntos(0);
        tab2.setUser(userService.getUserById("favilpae"));
        tab2.setUsos0(2);
        tab2.setUsos1(2);
        tab2.setUsos2(2);
        tab2.setUsos3(2);
        tab2.setUsos4(2);
        tab2.setUsos5(2);
        tableroService.saveTablero(tab2);
    }

    @BeforeEach
    public void SetPartida(){
        p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        p.setTableros(List.of(tab, tab2));
        p.setId(2);
        p.setIdCriterioA1(1);
        p.setIdCriterioA2(2);
        p.setIdCriterioB1(1);
        p.setIdCriterioB2(2);
        partidaService.savePartida(p); 
    }

    @Test
	public void shouldGetMaximo() {
		assertThat(this.partidaService.getMaximo(1, 5, 3, 2)).isEqualTo(5);
	}

    @Test
	public void shouldGetPartidaById() {
		Partida partidaById = this.partidaService.getPartidaById(2);
        assertThat(partidaById.getDateTime().equals(LocalDateTime.of(2023, 1, 4, 17, 55)));
	}

    @Test
        public void shouldSavePartida(){
        assertThat(this.partidaService.getPartidaById(10)).isNull();
        Partida part = new Partida();
        part.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        part.setId(10);
        part.setIdCriterioA1(2);
        part.setIdCriterioA2(3);
        part.setIdCriterioB1(4);
        part.setIdCriterioB2(3);
        partidaService.savePartida(part);
        assertThat(part.getIdCriterioB1()).isEqualTo(4);
    }

    @Test
	public void actualizaUso0() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.BOSQUE);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos0();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    // @Test
	// public void actualizaUso1() { 
    //     Turno t = new Turno();
    //     t.setTerritorio(Territorio.CASTILLO);
    //     turnoService.saveTurno(t);
    //     List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
    //     Integer usos = tab.getUsos1();
    //     Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
    //     assertThat(usos).isEqualTo(usos1+1);
    // }

    @Test
	public void shouldCreatePartidaSolitario() {
        User u = userService.getUserById("aitroddue");
        List<Integer> partidaSolitario = partidaService.crearPartidaSolitario(u);
        assertThat(partidaService.getPartidaById(partidaSolitario.get(0)).getTableros().size()).isEqualTo(1);
	}


}

     
