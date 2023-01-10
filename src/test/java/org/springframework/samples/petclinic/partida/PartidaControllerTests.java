package org.springframework.samples.petclinic.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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
	
	protected User diegarlin = new User();

	protected User jaigarlin = new User();

	protected Tablero tab = new Tablero();

	protected Partida p = new Partida();
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
		p.setIdCriterioB1(1);
		p.setIdCriterioB2(2);
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
	}

    @WithMockUser(value = "spring")
	@Test
	void testShowPartidaJugador() throws Exception {
		mockMvc.perform(get("/partidas/{username}", JAIGARLIN))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("tablero"))
		.andExpect(view().name("users/partida"));
	}
	

    @WithMockUser(value = "spring")
	@Test
	void testCrearPartida() throws Exception {
		mockMvc.perform(get("/partida/crearPartida") )
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/crearPartida"));
	}

}
