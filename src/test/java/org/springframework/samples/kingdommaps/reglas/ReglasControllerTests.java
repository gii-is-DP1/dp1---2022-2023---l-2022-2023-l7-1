package org.springframework.samples.kingdommaps.reglas;

import org.springframework.context.annotation.FilterType;
import org.springframework.samples.kingdommaps.configuration.SecurityConfiguration;
import org.springframework.samples.kingdommaps.web.RulesController;
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

@WebMvcTest(controllers = RulesController.class,
 excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
  classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ReglasControllerTests {
    

    @Autowired
	private MockMvc mockMvc;



    
    @WithMockUser(value = "spring")
	@Test
	void testShowRules() throws Exception {
		mockMvc.perform(get("/rules"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("username"))
        .andExpect(view().name("rules"));
	}
}
