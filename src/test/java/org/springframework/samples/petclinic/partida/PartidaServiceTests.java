package org.springframework.samples.petclinic.partida;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
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

    protected Partida p = new Partida();
    protected Tablero tab = new Tablero();

    @BeforeEach
    public void SetPartida(){
        p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        // p.getTableros().set(0, null)
        p.setId(2);
        p.setIdCriterioA1(2);
        p.setIdCriterioA2(1);
        p.setIdCriterioB1(5);
        p.setIdCriterioB2(3);
        p.setIdTablero2(1);
        p.setIdTablero3(2);   
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
	public void shouldSavePartida() {
        List<Partida> listaPartidas = new ArrayList<>();
        assertThat(listaPartidas.size()).isEqualTo(0);
        Partida p = new Partida();
        p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        p.setId(2);
        p.setIdCriterioA1(2);
        p.setIdCriterioA2(1);
        p.setIdCriterioB1(5);
        p.setIdCriterioB2(3);
        p.setIdTablero2(1);
        p.setIdTablero3(2);
        partidaService.savePartida(p);
		listaPartidas.add(p);
        assertThat(listaPartidas.size()).isEqualTo(1);
	}

    @Test
	public void actualizaUso() {
        // p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        // p.setId(2);
        // p.setIdCriterioA1(2);
        // p.setIdCriterioA2(1);
        // p.setIdCriterioB1(5);
        // p.setIdCriterioB2(3);
        // p.setIdTablero2(1);
        // p.setIdTablero3(2);   
        // partidaService.savePartida(p); 
        // Turno t = new Turno();
        // t.setTerritorio(Territorio.BOSQUE);
        // turnoService.saveTurno(t);
        // List<Territorio> listaTerritorios = new ArrayList<>();
        // listaTerritorios.add(Territorio.BOSQUE);
        // listaTerritorios.add(Territorio.CASTILLO);
        // listaTerritorios.add(Territorio.MONTANA);
        // listaTerritorios.add(Territorio.POBLADO);
        // listaTerritorios.add(Territorio.PRADERA);
        // listaTerritorios.add(Territorio.RIO);
        // listaTerritorios.add(Territorio.NA);
        // Tablero tab = new Tablero();
        // tab.setId(2);
        // tab.setUsos0(4);
        // tab.setPuntos(0);
        // tab.setPartidaEnCurso(true);
        // tab.setUser(userService.getUserById("aitroddue"));
        // tab.setPartidaCreada(true);
        // tab.setPartida(p);
        // tableroService.saveTablero(tab);
        // Integer usos = tab.getUsos0();
        // Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios);
        // assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void shouldCreatePartidaSolitario() {
        User u = userService.getUserById("aitroddue");
        List<Integer> partidaSolitario = partidaService.crearPartidaSolitario(u);
        assertThat(partidaService.getPartidaById(partidaSolitario.get(0)).getTableros().size()).isEqualTo(1);
	}


}
     
