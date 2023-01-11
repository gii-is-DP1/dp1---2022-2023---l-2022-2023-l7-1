package org.springframework.samples.petclinic.user;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = UserController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTests {

    private static final String USER_USERNAME = "diegarlin";

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@MockBean
    private TableroService tableroService;

	@MockBean
	private PartidaService partidaService;

	@Autowired
	private MockMvc mockMvc;

	private User user;
	protected Tablero tablero = new Tablero();
	protected Partida partida = new Partida();

	@BeforeEach
	void setup() {
		user = new User();
		user.setBirthDate(LocalDate.of(2002, 1, 2));
        user.setEmail("diegarlin@user.com");
        user.setLastName("Linares");
        user.setName("Diego");
        user.setUsername("diegarlin");
        user.setEnabled(true);
        user.setPassword("password");
        user.setPhone("633787878");
		given(this.userService.getUserById(USER_USERNAME)).willReturn(user);
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(view().name("users/createOrUpdateUserForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/users/new").param("name", "Diego").param("lastName", "Linares").with(csrf())
				.param("birthDate", "02/01/2002").param("email", "diegarlin@user.com").param("phone", "633787878")
                .param("username", "diegarlin").param("password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/users/new").with(csrf()).param("username", "diegarlin"))
        .andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
				.andExpect(model().attributeHasFieldErrors("user", "birthDate"))
				.andExpect(model().attributeHasFieldErrors("user", "name"))
                .andExpect(model().attributeHasFieldErrors("user", "lastName"))
				.andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(model().attributeHasFieldErrors("user", "password"))
				.andExpect(model().attributeHasFieldErrors("user", "phone"))
				.andExpect(view().name("users/createOrUpdateUserForm"));
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormDuplicateUsernameException() throws Exception {
		mockMvc.perform(post("/users/new").param("name", "Diego").param("lastName", "Linares").with(csrf())
				.param("birthDate", "02/01/2002").param("email", "diegarlin@user.com").param("phone", "633787878")
                .param("username", "diegarlin").param("password", "password"));
		mockMvc.perform(post("/users/new").param("name", "Diego").param("lastName", "Linares").with(csrf())
				.param("birthDate", "02/03/2002").param("email", "diegarlin@user.com").param("phone", "633787878")
                .param("username", "diegarlin").param("password", "password"));
		assertThat(DuplicatedUsernameException.class);
	}
	

	@WithMockUser(value = "spring")
	@Test
	void testShowAllUsers() throws Exception {
		Pageable p = PageRequest.of(0, 3);
		List<User> users = new ArrayList<>();
		Page<User> page = new PageImpl<User>(users, p, 0);
		given(this.userService.getAll(any())).willReturn(page);
		mockMvc.perform(get("/users/all")).andExpect(status().isOk())
		.andExpect(view().name("users/userListingPage"));
		users.add(user);
		Page<User> page2 = new PageImpl<User>(users, p, 0);
		given(this.userService.getAll(any())).willReturn(page2);
		mockMvc.perform(get("/users/all")).andExpect(status().isOk())
		.andExpect(view().name("users/userListingPage"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(get("/users/{username}/delete", USER_USERNAME).with(csrf())).andExpect(status().is3xxRedirection())
				.andExpect(model().attributeDoesNotExist("user"))
				.andExpect(view().name("redirect:/users/all"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShouldNotDeleteUser() throws Exception {
		given(this.tableroService.tieneUnaPartida(any())).willReturn(true);
		mockMvc.perform(get("/users/{username}/delete", USER_USERNAME).with(csrf())).andExpect(status().isOk())
				.andExpect(view().name("deleteUnsuccessful"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEditUserAsAdmin() throws Exception {
		mockMvc.perform(get("/users/{username}/edit", USER_USERNAME).with(csrf())).andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attribute("user", hasProperty("lastName", is("Linares"))))
				.andExpect(model().attribute("user", hasProperty("name", is("Diego"))))
				.andExpect(model().attribute("user", hasProperty("birthDate", is(LocalDate.of(2002, 1, 2)))))
				.andExpect(model().attribute("user", hasProperty("email", is("diegarlin@user.com"))))
				.andExpect(model().attribute("user", hasProperty("phone", is("633787878"))))
				.andExpect(model().attribute("user", hasProperty("password", is("password"))))
				.andExpect(model().attribute("user", hasProperty("username", is("diegarlin"))))
				.andExpect(view().name("users/userEdit"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessEditUserFormAsAdmin() throws Exception {
		mockMvc.perform(post("/users/{username}/edit", USER_USERNAME ).param("name", "Diego").param("lastName", "Linares").with(csrf())
		.param("birthDate", "02/01/2002").param("email", "diegarlin@user.com")
		.param("phone", "633787878").param("password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/users/all"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessEditUserFormAsAdminHasError() throws Exception {
		mockMvc.perform(post("/users/{username}/edit", USER_USERNAME ).param("name", "")
				.param("lastName", "").with(csrf())
				.param("email", ""))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
				.andExpect(model().attributeHasFieldErrors("user", "name"))
                .andExpect(model().attributeHasFieldErrors("user", "lastName"))
				.andExpect(model().attributeHasFieldErrors("user", "email"))
				.andExpect(model().attributeHasFieldErrors("user", "phone"))
				.andExpect(view().name("users/userEdit"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEditUserAsPlayer() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		mockMvc.perform(get("/user/diegarlin/userEdit").with(csrf())).andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attribute("user", hasProperty("lastName", is("Linares"))))
				.andExpect(model().attribute("user", hasProperty("name", is("Diego"))))
				.andExpect(model().attribute("user", hasProperty("birthDate", is(LocalDate.of(2002, 1, 2)))))
				.andExpect(model().attribute("user", hasProperty("email", is("diegarlin@user.com"))))
				.andExpect(model().attribute("user", hasProperty("phone", is("633787878"))))
				.andExpect(model().attribute("user", hasProperty("password", is("password"))))
				.andExpect(model().attribute("user", hasProperty("username", is("diegarlin"))))
				.andExpect(view().name("users/userEdit"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessEditUserFormAsPlayer() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		mockMvc.perform(post("/user/diegarlin/userEdit" ).param("name", "Diego").param("lastName", "Linares").with(csrf())
		.param("birthDate", "02/01/2002").param("email", "diegarlin@user.com")
		.param("phone", "633787878").param("password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessEditUserFormAsPlayerHasError() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		mockMvc.perform(post("/user/diegarlin/userEdit" ).param("name", "")
				.param("lastName", "").with(csrf())
				.param("email", ""))
				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("user"))
				.andExpect(model().attributeHasFieldErrors("user", "name"))
                .andExpect(model().attributeHasFieldErrors("user", "lastName"))
				.andExpect(model().attributeHasFieldErrors("user", "email"))
				.andExpect(model().attributeHasFieldErrors("user", "phone"))
				.andExpect(view().name("users/userEdit"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowAllStats() throws Exception {
		Pageable p = PageRequest.of(0, 3);
		List<User> users = new ArrayList<>();
		Page<User> page = new PageImpl<User>(users, p, 0);
		given(this.userService.getAll(any())).willReturn(page);
		mockMvc.perform(get("/stats")).andExpect(status().isOk())
		.andExpect(view().name("stats/stats"));
		users.add(user);
		Page<User> page2 = new PageImpl<User>(users, p, 0);
		given(this.userService.getAll(any())).willReturn(page2);
		mockMvc.perform(get("/stats")).andExpect(status().isOk())
		.andExpect(view().name("stats/stats"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowUserStats() throws Exception {
		mockMvc.perform(get("/stat")).andExpect(status().isOk())
		.andExpect(model().attributeExists("username"))
		.andExpect(view().name("stats/userStats"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowPartidasAmigo() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(user);
		given(this.userService.getFriends(any())).willReturn(users);
		mockMvc.perform(get("/friends/partidas")).andExpect(status().isOk())
		.andExpect(model().attributeExists("tableros"))
		.andExpect(view().name("users/friendsPartida"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/users/find")).andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/findUsers"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowUser() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		mockMvc.perform(get("/user")).andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/userDetails"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowFriends() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		mockMvc.perform(get("/friends")).andExpect(status().isOk())
		.andExpect(model().attributeExists("friends"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/friends"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteFriend() throws Exception {
		given(this.userService.getUserById(any())).willReturn(user);
		String usernameFriend = "jeszamgue";
		mockMvc.perform(get("/friends/{usernameFriend}/delete", usernameFriend).with(csrf())).andExpect(status().is3xxRedirection())
				.andExpect(model().attributeDoesNotExist("friends"))
				.andExpect(view().name("redirect:/friends"));
	} 

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.userService.findUsers("")).willReturn(Lists.newArrayList(user, new User()));

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("users/userListingFound"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormByUsername() throws Exception {
		given(this.userService.findUsers(USER_USERNAME)).willReturn(Lists.newArrayList(user));

		mockMvc.perform(get("/users").param("username", USER_USERNAME)).andExpect(status().isOk()).andExpect(view().name("users/userListingFound"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormNoUsersFound() throws Exception {
		mockMvc.perform(get("/users").param("username", "Unknown Surname")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("user", "username"))
				.andExpect(model().attributeHasFieldErrorCode("user", "username", "notFound"))
				.andExpect(view().name("users/findUsers"));
	}
}
    
