package org.springframework.samples.petclinic.chat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ChatController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ChatControllerTests {
    
    @MockBean
	private ChatService chatService;

    @MockBean
    private MensajeService mensajeService;

    @MockBean
    private PartidaService partidaService;

    @MockBean
    private UserService userService;

    @MockBean
    private TableroService tableroService;

    @Autowired
	private MockMvc mockMvc;

    protected Chat chat = new Chat();

    protected Partida p = new Partida();
    protected Partida p2 = new Partida();

    protected Mensaje msj = new Mensaje();

    protected User user = new User();

    protected Tablero tablero = new Tablero();
    protected Tablero tablero2 = new Tablero();

    @BeforeEach
	void setup() {

        p.setId(1);
		chat.setPartida(p);
        tablero.setPartida(p);
        p2.setId(2);
        tablero2.setPartida(p2);
        chat.setMensajes(new ArrayList<>());

	}

    @WithMockUser(value = "spring")
	@Test
	void testChatDePartida() throws Exception {

        given(chatService.getByPartidaId(any())).willReturn(chat);
        given(partidaService.getUserById(any())).willReturn(user);
        List<Tablero> tableros = List.of(tablero2);
        given(partidaService.getTablerosByUser(any())).willReturn(tableros);

        mockMvc.perform(get("/chat/{id}", 1))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

        tableros = List.of(tablero);
        given(partidaService.getTablerosByUser(any())).willReturn(tableros);
        mockMvc.perform(get("/chat/{id}", 1))
            .andExpect(status().isOk())
		 	.andExpect(model().attributeExists("user"))
			.andExpect(model().attributeExists("id"))
            .andExpect(model().attributeExists("chat"))
		 	.andExpect(view().name("partidas/chat"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testEscribirMensaje() throws Exception {

        given(chatService.getByPartidaId(any())).willReturn(chat);
        given(partidaService.getUserById(any())).willReturn(user);
        mockMvc.perform(get("/chat/escribirMensaje/{id}", 1))
            .andExpect(status().isOk())
		 	.andExpect(model().attributeExists("user"))
			.andExpect(model().attributeExists("id"))
            .andExpect(model().attributeExists("chat"))
            .andExpect(model().attributeExists("mensaje"))
		 	.andExpect(view().name("partidas/escribirMensaje"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testPostEscribirMensaje() throws Exception {
        given(chatService.getByPartidaId(any())).willReturn(chat);
        given(partidaService.getUserById(any())).willReturn(user);
        given(mensajeService.getUltimoId()).willReturn(2);
        mockMvc.perform(post("/chat/escribirMensaje/{id}", 1)
            .with(csrf())
            .param("id", "1")
            .param("contenido","sffsdf"))
            .andExpect(status().is3xxRedirection())
		 	.andExpect(view().name("redirect:/chat/1"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testPostEscribirMensajeHasErrors() throws Exception {
        mockMvc.perform(post("/chat/escribirMensaje/{id}", 1)
            .with(csrf())
            .param("id", "1")
            .param("contenido",""))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("mensaje"))
			.andExpect(model().attributeHasFieldErrors("mensaje", "contenido"))
		 	.andExpect(view().name("partidas/escribirMensaje"));
	}

}
