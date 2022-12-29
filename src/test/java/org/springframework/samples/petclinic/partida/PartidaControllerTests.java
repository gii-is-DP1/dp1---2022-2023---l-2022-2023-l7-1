package org.springframework.samples.petclinic.partida;

import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = PartidaController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class PartidaControllerTests {

    private static final String USER_USERNAME = "diegarlin";

    @Autowired
	private MockMvc mockMvc;

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


    @WithMockUser(value = "spring")
	@Test
	void testShowPartidaJugador() throws Exception {
		mockMvc.perform(get("/partidas/{username}", USER_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("tablero"))
		.andExpect(view().name("users/partida"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowPartidas() throws Exception {
		mockMvc.perform(get("/partidas")).andExpect(status().isOk())
		.andExpect(model().attributeExists("tablero"))
		.andExpect(view().name("users/partida"));
	}
    
}
