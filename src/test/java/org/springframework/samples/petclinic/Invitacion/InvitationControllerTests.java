package org.springframework.samples.petclinic.Invitacion;

import java.time.LocalDate;
import org.springframework.context.annotation.FilterType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        given(this.userService.getUserById(user1.getUsername())).willReturn(user1);        
        user2.setBirthDate(LocalDate.of(2002, 1, 2));
        user2.setEmail("diegarlin@user.com");
        user2.setLastName("Linares");
        user2.setName("Diego");
        user2.setUsername("lolito");
        user2.setEnabled(true);
        user2.setPassword("password");
        user2.setPhone("633787878");
        given(this.userService.getUserById(user2.getUsername())).willReturn(user2);
	}

    @WithMockUser(value = "spring")
	@Test
	void testShouldgetInvitationes() throws Exception{
        mockMvc.perform(get("/users/{username}/invitations", user1.getUsername())).andExpect(status().isOk())
				.andExpect(model().attributeExists("invitationsList"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("/users/invitations"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldgetUsersToInvite() throws Exception{
        mockMvc.perform(get("/users/{username}/invite", user1.getUsername())).andExpect(status().isOk())
				.andExpect(model().attributeExists("availableList"))
                .andExpect(model().attributeExists("username"))
				.andExpect(view().name("/users/invites"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldinvitateUser() throws Exception{
        mockMvc.perform(get("/users/{username1}/invitate/{username2}", user1.getUsername(), user2.getUsername())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/"+user1.getUsername()));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldacceptInvitation() throws Exception{
        mockMvc.perform(get("/users/{username}/accept/{id}", user1.getUsername(), id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/"+user1.getUsername()+"/friends"));
    }

    @WithMockUser(value = "spring")
	@Test
	void testShouldcancelInvitation() throws Exception{
        mockMvc.perform(get("/users/{username}/cancelInvite/{id}", user1.getUsername(), id)).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/"+user1.getUsername()+"/invitations"));
    }



    
}
