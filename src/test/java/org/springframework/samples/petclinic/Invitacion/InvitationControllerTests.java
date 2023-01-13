package org.springframework.samples.petclinic.Invitacion;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = InvitationController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class InvitationControllerTests {

    @MockBean
	private InvitationService invitationService;

    @MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

    protected User user1 = new User();
    protected User user2 = new User();
    protected Invitation inv1 = new Invitation();
    protected InvitationGame invG1 = new InvitationGame();
    protected List<Invitation> listinv1 = new ArrayList<>();
    protected List<InvitationGame> listinvG1 = new ArrayList<>();
    protected Tablero tab = new Tablero();
    protected Partida p = new Partida();

    private Integer id =1;


    @BeforeEach
	void setup() {
        user1.setBirthDate(LocalDate.of(2002, 1, 2));
        user1.setEmail("diegarlin@user.com");
        user1.setLastName("Linares");
        user1.setName("Diego");
        user1.setUsername("diegarlin");
        user1.setEnabled(true);
        user1.setPassword("password");
        user1.setPhone("633787878");        
        user2.setBirthDate(LocalDate.of(2002, 1, 2));
        user2.setEmail("diegarlin@user.com");
        user2.setLastName("Linares");
        user2.setName("Diego");
        user2.setUsername("lolito");
        user2.setEnabled(true);
        user2.setPassword("password");
        user2.setPhone("633787878");
        inv1.setId(1);
        inv1.setSender(user1);
        inv1.setReceiver(user2);
        listinv1.add(inv1);
        invG1.setAnfitrion(user1);
        invG1.setId(id);
        invG1.setPosibleJugador(user2);
        listinvG1.add(invG1);
        tab.setId(3);
        tab.setPartida(p);
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
        p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        p.setId(2);
        p.setIdCriterioA1(2);
        p.setIdCriterioA2(1);
        p.setIdCriterioB1(5);
        p.setIdCriterioB2(3);

	}

    @WithMockUser(value = "spring")
	@Test
	void testShouldgetInvitationes() throws Exception{
        given(invitationService.getInvitationsOf(anyString())).willReturn(listinv1);
        mockMvc.perform(get("/invitations/{username}", user1.getUsername())).andExpect(status().isOk())
				.andExpect(model().attributeExists("invitationsList"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("/users/invitations"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldgetUsersToInvite() throws Exception{
        given(invitationService.getAvailableUsers(anyString())).willReturn(List.of(user2));
        mockMvc.perform(get("/invite/{username}", user1.getUsername())).andExpect(status().isOk())
				.andExpect(model().attributeExists("availableList"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("/users/invites"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldinvitateUser() throws Exception{
        mockMvc.perform(get("/invitate/{usernameLogged}/{usernameReceiver}", user1.getUsername(), user2.getUsername())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/invitations/"+user1.getUsername()));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldacceptInvitation() throws Exception{
        mockMvc.perform(get("/invitationAccepted/{username}/{id}", user1.getUsername(), id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/friends/"+user1.getUsername()));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldcancelInvitation() throws Exception{
        mockMvc.perform(get("/invitationCancelled/{username}/{id}", user1.getUsername(), id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/invitations/"+user1.getUsername()));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldShowFriendsForLobby() throws Exception{
        given(this.invitationService.getAmigosDisponiblesParaJugar(anyString())).willReturn(List.of(user2));
        given(this.userService.getUserById(anyString())).willReturn(user1);
        mockMvc.perform(get("/lobby")).andExpect(status().isOk())
				.andExpect(model().attributeExists("friendsToPlay"))
                .andExpect(model().attributeExists("user"))
				.andExpect(view().name("partidas/lobby"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldgetInvitationsToGame() throws Exception{
        given(this.invitationService.getInvitationsGameOf(anyString())).willReturn(listinvG1);
        mockMvc.perform(get("/invitationsGame")).andExpect(status().isOk())
				.andExpect(model().attributeExists("invitationToPlay"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("/users/invitationsToPlay"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldinvitateUserToPlay() throws Exception{
        mockMvc.perform(get("/invitateToPlay/{posibleJugador}", user2.getUsername())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lobby"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldacceptInvitationToGame() throws Exception{
        mockMvc.perform(get("/invitationToGameAccepted/{id}", id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/salaDeEsperaJugadores"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldcancelInvitationToGame() throws Exception{
        mockMvc.perform(get("/invitationToGameCancelled/{id}",  id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldShowSalaDeEspera() throws Exception{
        given(userService.getUserById(anyString())).willReturn(user1);
        mockMvc.perform(get("/salaDeEspera")).andExpect(status().isOk())
				.andExpect(model().attributeExists("jugadoresAceptados"))
                .andExpect(model().attributeExists("username"))
                .andExpect(model().attribute("tamañoJugadores", is(0)))
				.andExpect(view().name("partidas/salaDeEspera"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldShowSalaDeEsperaJugadores() throws Exception{
        given(userService.getUserById(anyString())).willReturn(user1);
        given(invitationService.getTableroActiveUser(any())).willReturn(tab);
        mockMvc.perform(get("/salaDeEsperaJugadores")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/partida/Multijugador/espera/dado/2"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldShowSalaDeEsperaJugadoresNull() throws Exception{
        given(userService.getUserById(anyString())).willReturn(user1);
        given(invitationService.getTableroActiveUser(any())).willReturn(null);
        mockMvc.perform(get("/salaDeEsperaJugadores")).andExpect(status().isOk())
                .andExpect(model().attributeExists("jugadoresAceptados"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("partidas/esperaDeJugadores"));
    }
    
}
