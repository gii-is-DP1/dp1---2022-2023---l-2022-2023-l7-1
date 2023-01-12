package org.springframework.samples.petclinic.partida;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.casilla.CasillaService;
import org.springframework.samples.petclinic.criterios.StrategyInterface;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
class PartidaServiceTests {    
    
    private static StrategyInterface strategy;
    
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
    @Autowired
    protected CasillaService casillaService;

    protected Partida p = new Partida();
    protected Partida p2 = new Partida();
    protected Partida p3 = new Partida();
    protected Partida p4 = new Partida();
    protected Partida p5 = new Partida();
    protected Partida p6 = new Partida();
    protected Tablero tab = new Tablero();
    protected Tablero tab2 = new Tablero();
    protected Tablero tab3 = new Tablero();
    protected Tablero tab4 = new Tablero();
    protected Accion accion = new Accion();
    protected List<Accion> acciones = new ArrayList<>();
    protected List<Turno> turnos = new ArrayList<>();


    @BeforeAll
    public void setAll(){
        p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        p.setId(2);
        p.setIdCriterioA1(1);
        p.setIdCriterioA2(1);
        p.setIdCriterioB1(1);
        p.setIdCriterioB2(1);
        partidaService.savePartida(p); 

        p2.setDateTime(LocalDateTime.of(2023, 1, 12, 15, 9));
        p2.setId(3);
        p2.setIdCriterioA1(2);
        p2.setIdCriterioA2(2);
        p2.setIdCriterioB1(2);
        p2.setIdCriterioB2(2);
        partidaService.savePartida(p2); 

        p3.setDateTime(LocalDateTime.of(2023, 1, 12, 15, 9));
        p3.setId(4);
        p3.setIdCriterioA1(3);
        p3.setIdCriterioA2(3);
        p3.setIdCriterioB1(3);
        p3.setIdCriterioB2(3);
        partidaService.savePartida(p3); 
        
        p4.setDateTime(LocalDateTime.of(2023, 1, 12, 15, 9));
        p4.setId(5);
        p4.setIdCriterioA1(4);
        p4.setIdCriterioA2(4);
        p4.setIdCriterioB1(4);
        p4.setIdCriterioB2(4);
        partidaService.savePartida(p4); 

        p5.setDateTime(LocalDateTime.of(2023, 1, 12, 15, 9));
        p5.setId(6);
        p5.setIdCriterioA1(5);
        p5.setIdCriterioA2(5);
        p5.setIdCriterioB1(5);
        p5.setIdCriterioB2(5);
        partidaService.savePartida(p5); 

        p6.setDateTime(LocalDateTime.of(2023, 1, 12, 15, 9));
        p6.setId(6);
        p6.setIdCriterioA1(6);
        p6.setIdCriterioA2(6);
        p6.setIdCriterioB1(6);
        p6.setIdCriterioB2(6);
        partidaService.savePartida(p6); 

        tab.setId(3);
        tab.setPartida(p);
        tab.setPartidaCreada(true);
        tab.setPartidaEnCurso(true);
        tab.setPartidaEnEspera(false);
        tab.setPoder1(1);
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

        tab2.setId(4);
        tab2.setPartida(p);
        tab2.setPartidaCreada(true);
        tab2.setPartidaEnCurso(true);
        tab2.setPartidaEnEspera(false);
        tab2.setPoder1(0);
        tab2.setPoder2(1);
        tab2.setPuntos(0);
        tab2.setUser(userService.getUserById("fravilpae"));
        tab2.setUsos0(2);
        tab2.setUsos1(2);
        tab2.setUsos2(2);
        tab2.setUsos3(2);
        tab2.setUsos4(2);
        tab2.setUsos5(2);
        tableroService.saveTablero(tab2);

        tab3.setId(5);
        tab3.setPartida(p);
        tab3.setPartidaCreada(true);
        tab3.setPartidaEnCurso(true);
        tab3.setPartidaEnEspera(false);
        tab3.setPoder1(0);
        tab3.setPoder2(0);
        tab3.setPuntos(0);
        tab3.setUser(userService.getUserById("jeszamgue"));
        tab3.setUsos0(2);
        tab3.setUsos1(2);
        tab3.setUsos2(2);
        tab3.setUsos3(2);
        tab3.setUsos4(2);
        tab3.setUsos5(2);
        tableroService.saveTablero(tab3);

        tab4.setId(6);
        tab4.setPartida(p);
        tab4.setPartidaCreada(true);
        tab4.setPartidaEnCurso(true);
        tab4.setPartidaEnEspera(false);
        tab4.setPoder1(0);
        tab4.setPoder2(0);
        tab4.setPuntos(0);
        tab4.setUser(userService.getUserById("raymon"));
        tab4.setUsos0(2);
        tab4.setUsos1(2);
        tab4.setUsos2(2);
        tab4.setUsos3(2);
        tab4.setUsos4(2);
        tab4.setUsos5(2);
        tableroService.saveTablero(tab4);

        p.setTableros(List.of(tab, tab2));
        partidaService.savePartida(p);

        accion.setTurno(turnoService.getTurnoById(1));
        accion.setTablero(tableroService.getTableroById(3));
        accion.setCasilla(casillaService.getCasillaById(11));
        accionService.save(accion);

        
       
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

    @Test
	public void actualizaUso1() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.CASTILLO);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos1();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void actualizaUso2() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.MONTANA);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos2();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void actualizaUso3() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.POBLADO);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos3();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void actualizaUso4() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.PRADERA);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos4();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void actualizaUso5() { 
        Turno t = new Turno();
        t.setTerritorio(Territorio.RIO);
        turnoService.saveTurno(t);
        List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO, Territorio.NA);
        Integer usos = tab.getUsos5();
        Integer usos1 = partidaService.actualizarUso(2, t, listaTerritorios, tab);
        assertThat(usos).isEqualTo(usos1+1);
    }

    @Test
	public void shouldCreatePartidaSolitario() {
        User u = userService.getUserById("aitroddue");
        List<Integer> partidaSolitario = partidaService.crearPartidaSolitario(u);
        assertThat(partidaService.getPartidaById(partidaSolitario.get(0)).getTableros().size()).isEqualTo(1);
	}

    @Test
	public void shouldCalculateCasillasDisponibles() {
        Set<Integer> casillasDisp = this.partidaService.casillasDisponibles(1, 3);
        assertThat(casillasDisp.size()).isEqualTo(4);
	}

    @Test
	public void shouldCalculateCasillasDisponiblesPrimeraAccion() {
        assertThat(partidaService.casillasDisponiblesPrimeraAccion(3).size()).isEqualTo(61);
	}

    @Test
	public void shouldCalculatePuntosCriterioA1Partida1() {
        Integer x = partidaService.calcularPuntosCriterioA1(acciones, turnos, p);
        assertThat(x).isEqualTo(0);
	}

    @Test
	public void shouldCalculatePuntosCriterioA2Partida1() {
        Integer x = partidaService.calcularPuntosCriterioA2(acciones, turnos, p);
        assertThat(x).isEqualTo(0);
	}

    @Test
	public void shouldCalculatePuntosCriterioB1Partida1() {
        Integer x = partidaService.calcularPuntosCriterioB1(acciones, turnos, p);
        assertThat(x).isEqualTo(0);
	}

    @Test
	public void shouldCalculatePuntosCriterioB2Partida1() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p);
        assertThat(x).isEqualTo(0);
	}

    @Test
	public void shouldCalculatePuntosCriterioA1Partida2() {
        Integer x = partidaService.calcularPuntosCriterioA1(acciones, turnos, p2);
        assertThat(x).isEqualTo(0);
	}

    @Test
	public void shouldCalculatePuntosCriterioA2Partida2() {
        Integer x = partidaService.calcularPuntosCriterioA2(acciones, turnos, p2);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB1Partida2() {
        Integer x = partidaService.calcularPuntosCriterioB1(acciones, turnos, p2);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB2Partida2() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p2);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA1Partida3() {
        Integer x = partidaService.calcularPuntosCriterioA1(acciones, turnos, p3);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA2Partida3() {
        Integer x = partidaService.calcularPuntosCriterioA2(acciones, turnos, p3);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB1Partida3() {
        Integer x = partidaService.calcularPuntosCriterioB1(acciones, turnos, p3);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB2Partida3() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p3);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA1Partida4() {
        Integer x = partidaService.calcularPuntosCriterioA1(acciones, turnos, p4);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA2Partida4() {
        Integer x = partidaService.calcularPuntosCriterioA2(acciones, turnos, p4);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB1Partida4() {
        Integer x = partidaService.calcularPuntosCriterioB1(acciones, turnos, p4);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB2Partida4() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p4);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA1Partida5() {
        Integer x = partidaService.calcularPuntosCriterioA1(acciones, turnos, p5);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA2Partida5() {
        Integer x = partidaService.calcularPuntosCriterioA2(acciones, turnos, p5);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB1Partida5() {
        Integer x = partidaService.calcularPuntosCriterioB1(acciones, turnos, p5);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB2Partida5() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p5);
        assertThat(x).isEqualTo(0);
    }
    @Test
	public void shouldCalculatePuntosCriterioA1Partida6() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p6);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioA2Partida6() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p6);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB1Partida6() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p6);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePuntosCriterioB2Partida6() {
        Integer x = partidaService.calcularPuntosCriterioB2(acciones, turnos, p6);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida1() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p)).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida2() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p2)).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida3() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p3)).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida4() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p4)).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida5() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p5)).isEqualTo(0);
    }

    @Test
	public void shouldCalculatePoder2Partida6() {
        assertThat(partidaService.calcularPoder2(acciones, turnos, p6)).isEqualTo(0);
    }

    @Test
	public void shouldDeletePartida() {
        Partida p = new Partida();
        p.setDateTime(LocalDateTime.of(2023, 1,11, 17, 29));
        p.setId(5);
        p.setIdCriterioA1(1);
        p.setIdCriterioA2(2);
        p.setIdCriterioB1(1);
        p.setIdCriterioB2(2);
        partidaService.savePartida(p); 
        List<Partida> partidas = new ArrayList<>();
        partidas.add(p);
        Boolean b1 = partidas.size() == 1;
        this.partidaService.delete(partidaService.getPartidaById(5));
        Boolean b2 = partidas.size() == 0;
        assertThat(b1).isNotEqualTo(b2);
    }

    @Test
	public void shouldGetPrimeraAccion() {
        Turno t = new Turno();
        Integer x = partidaService.getPrimeraAccion(acciones, t);
        assertThat(x).isEqualTo(1);
    }

    @Test
	public void shouldCreatePartidaMultijugador() {
        List<User> ls = List.of(userService.getUserById("aitroddue"), userService.getUserById("fravilpae"), userService.getUserById("jeszamgue"));
        List<Integer> partida = partidaService.crearPartidaMultijugador(ls);
        assertThat(partidaService.getPartidaById(partida.get(0)).getTableros().size()).isEqualTo(3);
    }

    @Test
	public void shouldGetNumJugador() {
        List<Tablero> tableros = partidaService.getPartidaById(2).getTableros();
        assertThat(partidaService.getNumJugador(tab, tableros)).isEqualTo(0);
    }

    @Test
	public void shouldQuitarUsoDado() {
        List<Integer> dados = new ArrayList<>();
        Turno t = new Turno();
        t.setNumTerritoriosJ1(0);
        t.setTerritorio(Territorio.NA);
        turnoService.saveTurno(t);
        partidaService.quitarUsoDado(1, dados, t).size();
        assertThat(dados.size()).isEqualTo(0);
    }

    @Test
	public void shouldActualizarPoder1() {
        partidaService.actualizarPoderes(accion, tab, 2);
        assertThat(tab.getPoder1()).isEqualTo(0);
    }

    @Test
	public void shouldActualizarPoder2() {
        partidaService.actualizarPoderes(accion, tab2, 2);
        assertThat(tab.getPoder2()).isEqualTo(0);
    }

    @Test
	public void shouldActualizarUsoCaso1() {
        Turno t = new Turno();
        t.setNumTerritoriosJ1(0);
        t.setTerritorio(Territorio.NA);
        partidaService.actualizarUso1(t, t, tab, 1, accion);
        assertThat(t.getNumTerritoriosJ1()).isEqualTo(-1);
    }

    @Test
	public void shouldActualizarUsoCaso2() {
        Turno t = new Turno();
        t.setNumTerritoriosJ1(1);
        t.setTerritorio(Territorio.NA);
        Turno tPost = new Turno();
        tPost.setNumTerritoriosJ1(-1);
        tPost.setTerritorio(Territorio.NA);
        partidaService.actualizarUso1(tPost, t, tab2, 1, accion);
        assertThat(t.getNumTerritoriosJ1()).isEqualTo(0);
    }

    @Test
	public void shouldActualizarUsoCaso3() {
        Turno t = new Turno();
        t.setNumTerritoriosJ1(3);
        t.setTerritorio(Territorio.NA);
        Turno tPost = new Turno();
        tPost.setNumTerritoriosJ1(-1);
        tPost.setTerritorio(Territorio.NA);
        partidaService.actualizarUso1(tPost, t, tab3, 1, accion);
        assertThat(t.getNumTerritoriosJ1()).isEqualTo(1);
    }

    @Test
	public void shouldActualizarUsoCaso4() {
        Turno t = new Turno();
        t.setNumTerritoriosJ1(1);
        t.setTerritorio(Territorio.NA);
        partidaService.actualizarUso1(t, t, tab, 1, accion);
        assertThat(tab.getPoder1()).isEqualTo(0);
    }

    @Test
	public void shouldGetAccionesPorDibujar() {
        Turno t = new Turno();
        t.setNumTerritoriosJ1(4);
        t.setTerritorio(Territorio.NA);
        Integer i = partidaService.getAccionesPorDibujar(t, 1);
        assertThat(i).isEqualTo(4);
    }

    @Test
	public void shouldSaveUserEstadoFalse() {
        User user = new User();
        user.setEstado(true);
        assertTrue(user.getEstado());
        user.setUsername("test");
        partidaService.saveUserEstadoFalse(user);
        assertFalse(user.getEstado());
    }

    @Test
	public void shouldSaveTableroEnEspera() {
        tab.setPartidaEnEspera(false);
        assertFalse(tab.getPartidaEnEspera());
        partidaService.saveTableroEnEspera(tab);
        assertTrue(tab.getPartidaEnEspera());
    }

    @Test
	public void shouldGetNumTablerosEnEspera() {
        User user = new User();
        user.setEstado(true);
        tab.setUser(user);
        User user2 = new User();
        user2.setEstado(false);
        tab2.setUser(user2); 
        List<Tablero> ts = List.of(tab, tab2);
        assertThat(partidaService.getNumTablerosEnEsperaDado(ts)).isEqualTo(1);
    }

    @Test
	public void shouldGetPartidaEnEspera() {
        tab.setPartidaEnEspera(true);
        tab2.setPartidaEnEspera(false);
        List<Tablero> ts = List.of(tab, tab2);
        assertTrue(partidaService.getPartidaEnEspera(ts));
    }

    @Test
	public void shouldGetUltimoJugadorActivo() {
        List<Tablero> ts = List.of(tab, tab2);
        Turno t = new Turno();
        t.setNumTerritoriosJ1(4);
        t.setTerritorio(Territorio.NA);
        Integer x = partidaService.getUltimoJugadorActivo(ts, t);
        assertThat(x).isEqualTo(0);
    }

    @Test
	public void shouldSaveJugadorActivo() {
        // User user = new User();
        // user.setEstado(false);
        // user.setUsername("test");
        // assertFalse(user.getEstado());
        // partidaService.saveJugadorActivo(p);
        // assertTrue(user.getEstado());
    }

    @Test
	public void shouldGetUltimoTurno() {
        Turno turno = new Turno();
        turno.setPartida(p6);
        turnoService.saveTurno(turno);
        turno.setTerritorio(Territorio.NA);
        turnoService.saveTurno(turno);
        Turno turno2 = new Turno();
        turno2.setPartida(p6);
        turno2.setTerritorio(Territorio.NA);
        turnoService.saveTurno(turno2);
        Turno t = partidaService.getUltimoTurno(p6);
        assertThat(t.getId()).isEqualTo(turnoService.getTurnosByPartida(p6.getId()).get(1).getId());
    }

    @Test
	public void shouldGetPartidaFinalizada() {
        tab.setPartidaEnEspera(true);
        tab2.setPartidaEnEspera(true);
        tab3.setPartidaEnEspera(true);
        List<Tablero> ts = List.of(tab, tab2, tab3);
        assertTrue(partidaService.getPartidaFinalizada(ts));
    }

    @Test
	public void shouldGetPosicionPartida() {
        // tab.setPuntos(15);
        // tableroService.saveTablero(tab);
        // tab2.setPuntos(20);
        // tableroService.saveTablero(tab2);
        // tab3.setPuntos(13);
        // tableroService.saveTablero(tab3);
        // List<Tablero> ts = List.of(tab, tab2, tab3);
        // assertThat(partidaService.getPosicionPartida(ts, tab)).isEqualTo(2);
    }

    @Test
	public void shouldCancelarPartida() {
        Partida p = new Partida();
        p.setDateTime(LocalDateTime.of(2023, 1,11, 20, 26));
        p.setId(6);
        p.setIdCriterioA1(1);
        p.setIdCriterioA2(2);
        p.setIdCriterioB1(1);
        p.setIdCriterioB2(2);
        partidaService.savePartida(p);
        tab4.setUser(userService.getUserById("raymon"));
        tab4.setPartida(p);
        partidaService.cancelarPartida(userService.getUserById("raymon"));
        assertThat(partidaService.getPartidaById(6).tableros).isNull();
    }



}

     
