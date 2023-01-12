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
import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
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
public class PartidaControllerTests {

    private static final String DIEGARLIN = "diegarlin";

	private static final String JAIGARLIN = "jaigarlin";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

    @MockBean
	private PartidaService partidaService;

    @MockBean
	private UserService userService;

    @MockBean
	private TurnoService turnoService;

    @MockBean
	private AccionService accionService;

    @MockBean
	private TableroService tableroService;

	@MockBean
	private CasillaService casillaService;

	@MockBean
	protected HttpSession session; 
	
	protected User diegarlin = new User();

	protected User jaigarlin = new User();

	protected Tablero tab = new Tablero();

	protected Partida p = new Partida();

	protected Turno t = new Turno();

	protected Turno t2 = new Turno();

	protected Accion accion1 = new Accion();

	protected Accion accion2 = new Accion();

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
		
		Set<User> friendsDiego = new HashSet<>();
		friendsDiego.add(jaigarlin);
		Set<User> friendsJaime = new HashSet<>();
		friendsJaime.add(diegarlin);

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


		diegarlin.setFriends(friendsDiego);
		jaigarlin.setFriends(friendsJaime);

		p.setId(1);
		p.setIdCriterioA1(1);
		p.setIdCriterioA2(2);
		p.setIdCriterioB1(3);
		p.setIdCriterioB2(4);
		p.setDateTime(LocalDateTime.now());
		

		tab.setId(1);
		tab.setUser(jaigarlin);
		tab.setPartida(p);
		tab.setPartidaCreada(true);
		tab.setPartidaEnCurso(true);
		tab.setPartidaEnEspera(false);
		tab.setPoder1(0);
		tab.setPoder2(0);
		tab.setPuntos(0);
		tab.setUsos0(3);
		tab.setUsos1(3);
		tab.setUsos2(3);
		tab.setUsos3(3);
		tab.setUsos4(3);
		tab.setUsos5(0);
		p.setTableros(List.of(tab));

		accion1.setId(1);
		accion1.setTablero(tab);
		accion1.setTurno(t);
		accion1.setCasilla(casillaService.getCasillaById(1));

		accion2.setId(2);
		accion2.setTablero(tab);
		accion2.setTurno(t2);

		t.setId(1);
		t.setNumTerritoriosJ1(null);
		t.setNumTerritoriosJ2(null);
		t.setTerritorio(null);
		t.setPartida(p);

		t2.setId(2);
		t2.setPartida(p);
	}


	
	@WithMockUser(value = "spring")
	@Test
	void testShowPartidas() throws Exception {
		mockMvc.perform(get("/partidas")).andExpect(status().isOk())
		.andExpect(model().attributeExists("tablero"))
		.andExpect(view().name("users/partida"));
	}

	
	@WithMockUser(username =DIEGARLIN)
	@Test
	void testShowPartidaAmigo() throws Exception {
		mockMvc.perform(get("/partidas/partidaEnCurso/{username}/1", JAIGARLIN))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
		List<User> users = new ArrayList<>();
		users.add(diegarlin);
		given(this.userService.getFriends(any())).willReturn(users);
		given(this.userService.getUserById(any())).willReturn(diegarlin);
		given(this.tableroService.getTableroById(anyInt())).willReturn(tab);
		mockMvc.perform(get("/partidas/partidaEnCurso/{username}/1", JAIGARLIN))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("partidas/espectarPartida"));
		tab.setPartidaEnCurso(false);
		given(this.tableroService.getTableroById(anyInt())).willReturn(tab);
		mockMvc.perform(get("/partidas/partidaEnCurso/{username}/1", JAIGARLIN))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/resultados/1"));
		
		
	}

    @WithMockUser(value = "spring")
	@Test
	void testCrearPartida() throws Exception {
		mockMvc.perform(get("/partida/crearPartida") )
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/crearPartida"));
		List<User> users = new ArrayList<>();
		users.add(diegarlin);
		given(this.userService.getUserById(any())).willReturn(diegarlin);
		given(this.tableroService.getActivePlayers()).willReturn(users);
		mockMvc.perform(get("/partida/crearPartida") )
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/continuarCancelarPartida"));
		
	}

	

	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaAEligeTerritorio() throws Exception{
		given(partidaService.getTableroActiveByUser(any())).willReturn(tab);
		given(partidaService.getTablerosByPartidaId(anyInt())).willReturn(List.of(tab));
		given(partidaService.getAccionesByTablero(anyInt())).willReturn(List.of());
		given(partidaService.getTurnosByPartida(anyInt())).willReturn(List.of(t));
		mockMvc.perform(get("/partida/continuarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/eligeTerritorio/1/1/3"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaADibujar() throws Exception{
		given(partidaService.getTableroActiveByUser(any())).willReturn(tab);
		given(partidaService.getTablerosByPartidaId(anyInt())).willReturn(List.of(tab));
		given(partidaService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
		given(partidaService.getTurnosByPartida(anyInt())).willReturn(List.of(t));
		given(partidaService.getPrimeraAccion(any(), any())).willReturn(0);
		
		mockMvc.perform(get("/partida/continuarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/dibujar/1/1/1/3/0"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaADibujarPrimerTurno() throws Exception{
		given(partidaService.getTableroActiveByUser(any())).willReturn(tab);
		given(partidaService.getTablerosByPartidaId(anyInt())).willReturn(List.of(tab));
		given(partidaService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
		given(partidaService.getTurnosByPartida(anyInt())).willReturn(List.of(t));
		given(partidaService.getPrimeraAccion(any(), any())).willReturn(1);
		
		mockMvc.perform(get("/partida/continuarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/dibujar/1/1/1/3/1"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaAEligeTerritorio3() throws Exception{
		given(partidaService.getTableroActiveByUser(any())).willReturn(tab);
		given(partidaService.getTablerosByPartidaId(anyInt())).willReturn(List.of(tab));
		given(partidaService.getAccionesByTablero(anyInt())).willReturn(List.of(accion2));
		given(partidaService.getTurnosByPartida(anyInt())).willReturn(List.of(t));
		given(partidaService.getPrimeraAccion(any(), any())).willReturn(0);
		
		mockMvc.perform(get("/partida/continuarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/eligeTerritorio/1/1/3"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testContinuarPartidaAEligeTerritorio2() throws Exception{
		given(partidaService.getTableroActiveByUser(any())).willReturn(tab);
		given(partidaService.getTablerosByPartidaId(anyInt())).willReturn(List.of(tab));
		given(partidaService.getAccionesByTablero(anyInt())).willReturn(List.of(accion2));
		given(partidaService.getTurnosByPartida(anyInt())).willReturn(List.of(t2,t));
		given(partidaService.getPrimeraAccion(any(), any())).willReturn(0);
		
		mockMvc.perform(get("/partida/continuarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/eligeTerritorio/1/1/2"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testcancelarPartida() throws Exception{
		given(this.partidaService.getUserById(any())).willReturn(jaigarlin);
		mockMvc.perform(get("/partida/cancelarPartida"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testCrearPartidaSolitaria() throws Exception{
		given(userService.getUserById(anyString())).willReturn(jaigarlin);
		given(partidaService.crearPartidaSolitario(any())).willReturn(List.of(1, 2));
		mockMvc.perform(get("/partida/crearPartidaSolitaria"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/partida/eligeTerritorio/1/2/3"));

	}


	@WithMockUser(value = "spring")
	@Test
	void testGetEligeTerritorio3() throws Exception{
			given(turnoService.getTurnoById(1)).willReturn(t);
			given(partidaService.getPartidaById(1)).willReturn(p);
			given(accionService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
			given(session.getAttribute("dados")).willReturn(null);

			
			mockMvc.perform(get("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}", 1 , 1, 3))
			.andExpect(status().isOk())
			.andExpect(model().attribute("dados", hasSize(3)))
			.andExpect(model().attribute("eligeTerritorio", is(true)))
			.andExpect(model().attributeExists("territorios"))
			.andExpect(model().attributeExists("poder1"))
			.andExpect(model().attributeExists("acciones"))
			.andExpect(model().attributeExists("turno"))
			.andExpect(model().attributeExists("criterios"))
			.andExpect(view().name("partidas/eligeTerritorio"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testGetEligeTerritorio2() throws Exception{
			given(turnoService.getTurnoById(1)).willReturn(t);
			given(partidaService.getPartidaById(1)).willReturn(p);
			given(accionService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
			given(session.getAttribute("dados")).willReturn(null);

			
			mockMvc.perform(get("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}", 1 , 1, 2))
			.andExpect(status().isOk())
			.andExpect(model().attribute("dados", hasSize(2)))
			.andExpect(model().attribute("eligeTerritorio", is(false)))
			.andExpect(model().attributeDoesNotExist("territorios"))
			.andExpect(model().attributeExists("poder1"))
			.andExpect(model().attributeExists("acciones"))
			.andExpect(model().attributeExists("turno"))
			.andExpect(model().attributeExists("criterios"))
			.andExpect(view().name("partidas/eligeTerritorio"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testPostEligeTerritorioError() throws Exception{
		mockMvc
		.perform(post("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}",1,1,3)
		.param("numTerritoriosJ1", "2").param("Territorio", "A").with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("partidas/eligeTerritorio"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testPostEligeTerritorio3() throws Exception{
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(partidaService.getPartidaById(anyInt())).willReturn(p);
		
		mockMvc
		.perform(post("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}",1,1,3)
		.param("numTerritoriosJ1", "2").param("Territorio", "BOSQUE").with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/partida/dibujar/1/1/null/3/1"));

		assertThat(t.getNumTerritoriosJ1()).isEqualTo(2);
		assertThat(t.getTerritorio()).isEqualTo(Territorio.BOSQUE);

	}

	@WithMockUser(value = "spring")
	@Test
	void testPostEligeTerritorioTOResultados() throws Exception{
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(partidaService.getPartidaById(anyInt())).willReturn(p);
		given(partidaService.actualizarUso(any(),any(),any(),any())).willReturn(-1);

		mockMvc
		.perform(post("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}",1,1,3)
		.param("numTerritoriosJ1", "2").param("Territorio", "BOSQUE").with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/partida/resultados/1"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testPostEligeTerritorio2() throws Exception{
		//Debe poner montaña y numTerritorios como 2

		// given(turnoService.getTurnoById(anyInt())).willReturn(t);
		// given(partidaService.getPartidaById(anyInt())).willReturn(p);
		// given(session.getAttribute(anyString())).willReturn(List.of(2,3));
		// given(partidaService.actualizarUso(any(),any(),any(),any())).willReturn(0);
		
		// mockMvc
		// .perform(post("/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}",1,1,2)
		// .param("numTerritoriosJ1", "2").param("Territorio","BOSQUE").with(csrf()))
		// 	.andExpect(status().isOk())
		// 	.andExpect(view().name("redirect:/partida/dibujar/1/1/null/3/1"));

		// assertThat(t.getNumTerritoriosJ1()).isEqualTo(2);
		// assertThat(t.getTerritorio()).isEqualTo(Territorio.MONTANA);
	}

	@WithMockUser(value = "spring")
	@Test
	void testGetDibujarPrimeraAccion() throws Exception{
		t.setNumTerritoriosJ1(2);
		given(partidaService.getPartidaById(1)).willReturn(p);
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(accionService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
		given(partidaService.casillasDisponiblesPrimeraAccion(anyInt())).willReturn(Set.of(1,2));

		mockMvc.perform(get("/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}",1,1,1,3,1))
		.andExpect(status().isOk())
		.andExpect(model().attribute("porDibujar", is(2)))
		.andExpect(model().attributeExists("acciones"))
		.andExpect(model().attributeExists("action"))
		.andExpect(model().attribute("casillas", is(Set.of(1,2))))
		.andExpect(model().attributeExists("tablero"))
		.andExpect(model().attributeExists("poder1"))
		.andExpect(model().attributeExists("poder"))
		.andExpect(model().attributeExists("turno"))
		.andExpect(view().name("partidas/dibujar"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testGetDibujarNOTPrimeraAccion() throws Exception{
		t.setNumTerritoriosJ1(2);
		given(partidaService.getPartidaById(1)).willReturn(p);
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(accionService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
		given(partidaService.casillasDisponibles(t.getId(),tab.getId())).willReturn(Set.of(1,2,3));

		mockMvc.perform(get("/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}",1,1,1,3,0))
		.andExpect(status().isOk())
		.andExpect(model().attribute("porDibujar", is(2)))
		.andExpect(model().attributeExists("acciones"))
		.andExpect(model().attributeExists("action"))
		.andExpect(model().attribute("casillas", is(Set.of(1,2,3))))
		.andExpect(model().attributeExists("tablero"))
		.andExpect(model().attributeExists("poder1"))
		.andExpect(model().attributeExists("poder"))
		.andExpect(model().attributeExists("turno"))
		.andExpect(view().name("partidas/dibujar"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testGetDibujarFinalizada() throws Exception{
		given(partidaService.getPartidaById(1)).willReturn(p);
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(accionService.getAccionesByTablero(anyInt())).willReturn(List.of(accion1));
		given(partidaService.casillasDisponibles(t.getId(),tab.getId())).willReturn(Set.of());

		mockMvc.perform(get("/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}",1,1,1,3,0))
		.andExpect(status().is3xxRedirection())
		.andExpect(model().attributeDoesNotExist("casillas"))
		.andExpect(model().attributeDoesNotExist("tablero"))
		.andExpect(model().attributeDoesNotExist("poder1"))
		.andExpect(model().attributeDoesNotExist("poder"))
		.andExpect(model().attributeDoesNotExist("turno"))
		.andExpect(view().name("redirect:/partida/resultados/1"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testPostDibujarADibujar() throws Exception{
		t.setNumTerritoriosJ1(1);
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(accionService.getAccionById(anyInt())).willReturn(accion1);
		given(partidaService.getPartidaById(anyInt())).willReturn(p);

		mockMvc
		.perform(post("/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}",1,1,1,3,1)
		.param("casilla", "2").with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/partida/dibujar/1/1/null/3/0"));

		assertThat(accion1.getCasilla().getId()).isEqualTo(2);
	}

	@WithMockUser(value = "spring")
	@Test
	void testPostDibujarAEligeTerritorio() throws Exception{
		t.setNumTerritoriosJ1(0);
		given(turnoService.getTurnoById(anyInt())).willReturn(t);
		given(accionService.getAccionById(anyInt())).willReturn(accion1);
		given(partidaService.getPartidaById(anyInt())).willReturn(p);

		mockMvc
		.perform(post("/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}/{primeraAccion}",1,1,1,3,1)
		.param("casilla", "3").with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/partida/eligeTerritorio/1/null/2"));

			assertThat(accion1.getCasilla().getId()).isEqualTo(3);
	}


	@WithMockUser(value = "spring")
	@Test
	void testResultados() throws Exception{
		given(tableroService.getTableroById(anyInt())).willReturn(tab);
		given(partidaService.calcularPuntosCriterioA1(any(),any(),any())).willReturn(2);
		given(partidaService.calcularPuntosCriterioA2(any(),any(),any())).willReturn(3);
		given(partidaService.calcularPuntosCriterioB1(any(),any(),any())).willReturn(4);
		given(partidaService.calcularPuntosCriterioB2(any(),any(),any())).willReturn(5);


		mockMvc.perform(get("/partida/resultados/{idTablero}",1))
		.andExpect(status().isOk())
		.andExpect(model().attribute("criterioA1", 2))
		.andExpect(model().attribute("criterioA2", 3))
		.andExpect(model().attribute("criterioB1", 4))
		.andExpect(model().attribute("criterioB2", 5))
		.andExpect(model().attribute("poder2", 0))
		.andExpect(model().attribute("puntosTotales", 14))
		.andExpect(model().attributeExists("acciones"))
		.andExpect(model().attributeExists("criterios"))
		.andExpect(view().name("partidas/resultados"));

		assertThat(tab.getPartidaEnCurso()).isFalse();
	}
}
