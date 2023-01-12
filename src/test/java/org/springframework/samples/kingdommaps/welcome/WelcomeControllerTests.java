package org.springframework.samples.kingdommaps.welcome;

import org.springframework.context.annotation.FilterType;
import org.springframework.samples.kingdommaps.configuration.SecurityConfiguration;
import org.springframework.samples.kingdommaps.web.WelcomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = WelcomeController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class WelcomeControllerTests {
    
    @Autowired
	private MockMvc mockMvc;

    @WithMockUser(value = "spring")
	@Test
	void testShowWelcome() throws Exception {
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("persons"))
        .andExpect(model().attributeExists("title"))
        .andExpect(model().attributeExists("group"))
        .andExpect(model().attributeExists("username"))
        .andExpect(view().name("welcome"));

        mockMvc.perform(get("/welcome"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("persons"))
        .andExpect(model().attributeExists("title"))
        .andExpect(model().attributeExists("group"))
        .andExpect(model().attributeExists("username"))
        .andExpect(view().name("welcome"));
	}
}
