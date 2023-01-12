package org.springframework.samples.kingdommaps.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.accion.AccionService;
import org.springframework.samples.kingdommaps.casilla.CasillaService;
import org.springframework.samples.kingdommaps.partida.Partida;
import org.springframework.samples.kingdommaps.partida.PartidaService;
import org.springframework.samples.kingdommaps.tablero.Tablero;
import org.springframework.samples.kingdommaps.tablero.TableroService;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.turnos.TurnoService;
import org.springframework.samples.kingdommaps.user.User;
import org.springframework.samples.kingdommaps.user.UserService;
import org.springframework.samples.kingdommaps.util.Territorio;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.BDDMockito.given;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class PartidaMultijugadorTests {

    private static final String DIEGARLIN = "diegarlin";

	private static final String JAIGARLIN = "jaigarlin";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

    @MockBean
	private PartidaService partidaService;

	@MockBean
	private CasillaService casillaService;

	@MockBean
	protected HttpSession session; 
	
	protected User diegarlin = new User();

	protected User jaigarlin = new User();

    protected List<Tablero> tableros = new ArrayList<>();

	protected Tablero tablero = new Tablero();

    protected Tablero tablero2 = new Tablero();

	protected Partida p = new Partida();

	protected Turno t = new Turno();
	
	protected Turno t2 = new Turno();
	
	protected Turno t3 = new Turno();

	protected Accion accion1 = new Accion();

	protected Accion accion2 = new Accion();

	protected Accion accion3 = new Accion();
	
	protected Accion accion4 = new Accion();
	
	protected List<Turno> turnos = new ArrayList<>();
	
	protected List<Accion> acciones = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();

		diegarlin.setUsername(DIEGARLIN);
		diegarlin.setBirthDate(LocalDate.of(2002, 07, 15));
		diegarlin.setEmail("garlin@gmail.com");
		diegarlin.setEnabled(false);
		diegarlin.setEstado(false);
		diegarlin.setName("Diego");
		diegarlin.setLastName("Linares");

		jaigarlin.setUsername(JAIGARLIN);
		jaigarlin.setBirthDate(LocalDate.of(1996,06,9));
		jaigarlin.setEmail("jaigarlin@gmail.com");
		jaigarlin.setEnabled(false);
		jaigarlin.setEstado(false);
		jaigarlin.setName("Jaime");		
		jaigarlin.setLastName("Linares");

		p.setId(1);
		p.setIdCriterioA1(1);
		p.setIdCriterioA2(2);
		p.setIdCriterioB1(3);
		p.setIdCriterioB2(4);
		p.setDateTime(LocalDateTime.now());
		

		tablero.setId(1);
		tablero.setUser(jaigarlin);
		tablero.setPartida(p);
		tablero.setPartidaCreada(true);
		tablero.setPartidaEnCurso(true);
		tablero.setPartidaEnEspera(false);
		tablero.setPoder1(0);
		tablero.setPoder2(0);
		tablero.setPuntos(0);
		tablero.setUsos0(3);
		tablero.setUsos1(3);
		tablero.setUsos2(3);
		tablero.setUsos3(3);
		tablero.setUsos4(3);
		tablero.setUsos5(0);

        tablero2.setId(1);
		tablero2.setUser(jaigarlin);
		tablero2.setPartida(p);
		tablero2.setPartidaCreada(true);
		tablero2.setPartidaEnCurso(true);
		tablero2.setPartidaEnEspera(false);
		tablero2.setPoder1(0);
		tablero2.setPoder2(0);
		tablero2.setPuntos(0);
		tablero2.setUsos0(3);
		tablero2.setUsos1(3);
		tablero2.setUsos2(3);
		tablero2.setUsos3(3);
		tablero2.setUsos4(3);
		tablero2.setUsos5(0);
        tableros.add(tablero);
        tableros.add(tablero2);
        p.setTableros(tableros);

		accion1.setId(1);
		accion1.setTablero(tablero);
		accion1.setTurno(t);
		accion1.setCasilla(casillaService.getCasillaById(1));
		
		accion2.setId(2);
		accion2.setTablero(tablero);
		accion2.setTurno(t2);
		accion2.setCasilla(casillaService.getCasillaById(2));
		
		accion4.setId(4);
		accion4.setTablero(tablero);
		accion4.setTurno(t2);
		accion4.setCasilla(casillaService.getCasillaById(51));
		
		accion3.setId(3);
		accion3.setTablero(tablero);
		accion3.setTurno(t2);
		accion3.setCasilla(null);

		t.setId(1);
		t.setNumTerritoriosJ1(2);
		t.setNumTerritoriosJ2(3);
		t.setTerritorio(Territorio.BOSQUE);
		
		t2.setId(2);
		t2.setNumTerritoriosJ1(2);
		t2.setNumTerritoriosJ2(3);
		t2.setTerritorio(Territorio.BOSQUE);
		
		t3.setId(3);
		t3.setNumTerritoriosJ1(null);
		t3.setNumTerritoriosJ2(null);
		t3.setNumTerritoriosJ1(null);
		t3.setNumTerritoriosJ2(null);
		t3.setTerritorio(Territorio.BOSQUE);
	}


	
	@WithMockUser(value = "spring")
	@Test
	void testCrearPartida() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(diegarlin);
		given(this.partidaService.getUserById(any())).willReturn(diegarlin);
		given(this.partidaService.getTableroActiveByUser(any())).willReturn(tablero);
        given(this.partidaService.getUltimoTurno(any())).willReturn(t);
        List<Integer> x = new ArrayList<>();
        x.add(null);
        x.add(null);
        given(this.partidaService.crearPartidaMultijugador(any())).willReturn(x);
		mockMvc.perform(get("/partida/crearPartidaMultijugador"))
		.andExpect(status().is3xxRedirection());
		x.clear();
		x.add(1);
        x.add(1);
        given(this.partidaService.crearPartidaMultijugador(any())).willReturn(x);
		mockMvc.perform(get("/partida/crearPartidaMultijugador"))
		.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaMultijugador() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(diegarlin);
		given(this.partidaService.getTablerosByPartidaId(any())).willReturn(tableros);
		given(this.partidaService.getTableroActiveByUser(any())).willReturn(tablero);
		turnos.add(t);
		given(this.partidaService.getTurnosByPartida(any())).willReturn(turnos);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		given(this.partidaService.getUserById(any())).willReturn(diegarlin);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		diegarlin.setEstado(true);
		given(this.partidaService.getUserById(any())).willReturn(diegarlin);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		acciones.add(accion3);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		given(this.partidaService.getPrimeraAccion(any(), any())).willReturn(1);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		turnos.add(t2);
		given(this.partidaService.getTurnosByPartida(any())).willReturn(turnos);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		acciones.clear();
		acciones.add(accion1);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		diegarlin.setEstado(false);
		given(this.partidaService.getUserById(any())).willReturn(diegarlin);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		acciones.add(accion2);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
		turnos.add(t3);
		given(this.partidaService.getTurnosByPartida(any())).willReturn(turnos);
		acciones.add(accion4);
		given(this.partidaService.getAccionesByTablero(any())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/continuarPartida")).andExpect(status().is3xxRedirection());
	}
    
	@WithMockUser(value = "spring")
	@Test
	void testEligeTerritorio() throws Exception {
		given(this.partidaService.getTurnoById(anyInt())).willReturn(t);
		given(this.partidaService.getPartidaById(anyInt())).willReturn(p);
		given(this.partidaService.getTableroActiveByUser(any())).willReturn(tablero);
		given(this.partidaService.getTablerosByPartidaId(anyInt())).willReturn(tableros);
		given(this.partidaService.getNumJugador(any(), any())).willReturn(1);
		acciones.add(accion1);
		acciones.add(accion2);
		given(this.partidaService.getAccionesByTablero(anyInt())).willReturn(acciones);
		given(session.getAttribute("dados")).willReturn(null);
		mockMvc.perform(get("/partida/Multijugador/eligeTerritorio/{idPartida}/{idTurno}", 1 , 1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("dados", hasSize(3)))
			.andExpect(model().attribute("eligeTerritorio", is(true)))
			.andExpect(model().attributeExists("territorios"))
			.andExpect(model().attributeExists("poder1"))
			.andExpect(model().attributeExists("acciones"))
			.andExpect(model().attributeExists("turno"))
			.andExpect(model().attributeExists("criterios"))
			.andExpect(model().attributeExists("usos"))
			.andExpect(model().attributeExists("numJugador"))
			.andExpect(model().attributeExists("chatId"))
			.andExpect(model().attributeExists("username"))
			.andExpect(view().name("partidas/eligeTerritorioMultijugador"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEligeNumTerritorio() throws Exception {
		given(this.partidaService.getPartidaById(anyInt())).willReturn(p);
		given(this.partidaService.getTurnoById(anyInt())).willReturn(t);
		given(this.partidaService.getTableroActiveByUser(any())).willReturn(tablero);
		given(this.partidaService.getTablerosByPartidaId(anyInt())).willReturn(tableros);
		given(this.partidaService.getNumJugador(any(), any())).willReturn(2);
		acciones.add(accion1);
		acciones.add(accion2);
		given(this.partidaService.getAccionesByTablero(anyInt())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/dado/{idPartida}/{idTurno}", 1 , 1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("poder1"))
			.andExpect(model().attributeExists("acciones"))
			.andExpect(model().attributeExists("turno"))
			.andExpect(model().attributeExists("criterios"))
			.andExpect(model().attributeExists("usos"))
			.andExpect(model().attributeExists("numJugador"))
			.andExpect(model().attributeExists("chatId"))
			.andExpect(model().attributeExists("username"))
			.andExpect(view().name("partidas/elegirDado"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEsperaResultados() throws Exception {
		given(this.partidaService.getPartidaById(anyInt())).willReturn(p);
		given(this.partidaService.getTablerosByPartidaId(anyInt())).willReturn(tableros);
		given(this.partidaService.getPartidaFinalizada(any())).willReturn(true);
		given(this.partidaService.getTableroActiveByUser(any())).willReturn(tablero);
		mockMvc.perform(get("/partida/Multijugador/espera/resultados/{idPartida}", 1))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/partida/resultados/"+tablero.getId()));

		given(this.partidaService.getPartidaFinalizada(any())).willReturn(false);
		acciones.add(accion1);
		acciones.add(accion2);
		given(this.partidaService.getAccionesByTablero(anyInt())).willReturn(acciones);
		mockMvc.perform(get("/partida/Multijugador/espera/resultados/{idPartida}", 1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("poder1"))
			.andExpect(model().attributeExists("acciones"))
			.andExpect(model().attributeExists("criterios"))
			.andExpect(model().attributeExists("usos"))
			.andExpect(model().attributeExists("chatId"))
			.andExpect(view().name("partidas/esperaTerritorio"));
	}
}
