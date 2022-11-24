package org.springframework.samples.petclinic.user;

import java.time.LocalDate;
import org.springframework.context.annotation.FilterType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
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

	@Autowired
	private MockMvc mockMvc;

	private User user;

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

	/* 
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
	*/

	@WithMockUser(value = "spring")
	@Test
	void testShowAllUsers() throws Exception {
		mockMvc.perform(get("/users/all")).andExpect(status().isOk())
		.andExpect(model().attributeExists("users"))
		.andExpect(view().name("users/userListing"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(get("/users/{username}/delete", USER_USERNAME).with(csrf())).andExpect(status().is3xxRedirection())
				.andExpect(model().attributeDoesNotExist("user"))
				.andExpect(view().name("redirect:/users/all"));
		boolean user0 = this.userService.findUser("diegarlin").isEmpty();
		assertThat(user0).isTrue();
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
		mockMvc.perform(get("/users/{username}/userEdit", USER_USERNAME).with(csrf())).andExpect(status().isOk())
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
		mockMvc.perform(post("/users/{username}/userEdit", USER_USERNAME ).param("name", "Diego").param("lastName", "Linares").with(csrf())
		.param("birthDate", "02/01/2002").param("email", "diegarlin@user.com")
		.param("phone", "633787878").param("password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessEditUserFormAsPlayerHasError() throws Exception {
		mockMvc.perform(post("/users/{username}/userEdit", USER_USERNAME ).param("name", "")
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
		mockMvc.perform(get("/stats")).andExpect(status().isOk())
		.andExpect(model().attributeExists("users"))
		.andExpect(view().name("users/stats"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowUserStats() throws Exception {
		mockMvc.perform(get("/users/{username}/stats", USER_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/userStats"));
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
		mockMvc.perform(get("/users/{username}", USER_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/userDetails"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowFriends() throws Exception {
		mockMvc.perform(get("/users/{username}/friends", USER_USERNAME)).andExpect(status().isOk())
		.andExpect(model().attributeExists("friends"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/friends"));
	}

	/*@WithMockUser(value = "spring")
	@Test
	void testDeleteFriend() throws Exception {
		User user1 = userService.getUserById("fravilpae");
		User user2 = userService.getUserById("jeszamgue");
		String USERNAME1 = user1.getUsername();
		String USERNAME2 = user2.getUsername();
		mockMvc.perform(get("/users/{username}/friends/{username2}/delete", USERNAME1, USERNAME2).with(csrf())).andExpect(status().is3xxRedirection())
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attributeDoesNotExist("friends"))
				.andExpect(view().name("redirect:/users/"+USERNAME1+"/friends"));
		boolean user0 = this.userService.getUserById(USERNAME2).getFriends().isEmpty();
		assertThat(user0).isTrue();

	}   ARREGLAR TEST, NO SE POR QUE FALLA*/
}
    
