package org.springframework.samples.petclinic.logros;

import java.time.LocalDate;
import org.springframework.context.annotation.FilterType;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = LogroController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class LogrosControllerTests {
    
    @MockBean
	private LogroService logroService;

    @MockBean
	private UserService userService;

    @Autowired
	private MockMvc mockMvc;

    @WithMockUser(value = "spring")
	@Test
	void testShowAllLogros() throws Exception {
		mockMvc.perform(get("/logros").with(csrf())).andExpect(status().isOk());
	}

    @WithMockUser(value = "spring")
	@Test
	void testDeleteLogro() throws Exception {
		// mockMvc.perform(get("/logros/{id}/delete", this.logroService.getById(1).getId()).with(csrf())).andExpect(status().is3xxRedirection())
		// 		.andExpect(model().attributeDoesNotExist("logro"))
		// 		.andExpect(view().name("redirect:/logros"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testEditLogro() throws Exception {
		// mockMvc.perform(get("/logros/{id}/edit", this.logroService.getById(1).getId()).with(csrf())).andExpect(status().isOk())
		// 		.andExpect(model().attributeExists("logro"))
		// 		.andExpect(model().attribute("user", hasProperty("titulo", is("Explorador de Patio"))))
		// 		.andExpect(model().attribute("user", hasProperty("descripcion", is("Consigue una puntuacion de <puntos> puntos o mas en una partida"))))
		// 		.andExpect(model().attribute("user", hasProperty("req_puntos", is(80))))
		// 		.andExpect(model().attribute("user", hasProperty("logo", is("logo1.png"))))
		// 		.andExpect(view().name("/logros/editLogro"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessEditLogro() throws Exception {
		// mockMvc.perform(post("/logros/{id}/edit", this.logroService.getById(1).getId())
        // .param("titulo", "Explorador de Patio")
        // .param("descripcion", "Consigue una puntuacion de <puntos> puntos o mas en una partida").with(csrf())
		// .param("req_puntos", "80")
		// .param("logo", "logo1.png"))
		// 		.andExpect(status().is3xxRedirection())
		// 		.andExpect(view().name("redirect:/logros"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessEditLogroHasError() throws Exception {
		// mockMvc.perform(post("/logros/{id}/edit", this.logroService.getById(1) ).param("titulo", "")
		// 		.param("descripcion", "").with(csrf())
		// 		.param("logo", ""))
		// 		.andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
		// 		.andExpect(model().attributeHasFieldErrors("user", "titulo"))
        //         .andExpect(model().attributeHasFieldErrors("user", "descripcion"))
		// 		.andExpect(model().attributeHasFieldErrors("user", "logo"))
		// 		.andExpect(model().attributeHasFieldErrors("user", "req_puntos"))
		// 		.andExpect(view().name("/logros/editLogro"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/logros/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("logro"))
				.andExpect(view().name("/logros/newLogro"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/logros/new").param("titulo", "Explorador nato")
                .param("descripcion", "Consigue la mayor cantidad de puntos").with(csrf())
				.param("logo", "logo5.png")
                .param("req_puntos", "170"))
				.andExpect(status().isOk())
				.andExpect(view().name("/logros/newLogro"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/logros/new").with(csrf()).param("titulo", "Explorador nato"))
        .andExpect(status().isOk()).andExpect(model().attributeHasErrors("logro"))
				.andExpect(model().attributeHasFieldErrors("logro", "descripcion"))
                .andExpect(model().attributeHasFieldErrors("logro", "logo"))
				.andExpect(view().name("/logros/newLogro"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testShowLogrosUsuario() throws Exception {
		mockMvc.perform(get("/logrosUsuario", this.userService.getUserById("aitroddue"))).andExpect(status().isOk())
		.andExpect(model().attributeExists("logrosUser"))
		.andExpect(model().attributeExists("username"))
		.andExpect(view().name("logros/userLogros"));
	}
}
