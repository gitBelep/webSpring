package webSpring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import webSpring.backend.BackendConfig;
import webSpring.backend.EmpService;
import webSpring.controller.WebConfig;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        {@ContextConfiguration(classes = BackendConfig.class),
        @ContextConfiguration(classes = WebConfig.class)})
@WebAppConfiguration
public class EmpIntegrationsTest {
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private EmpService empService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        empService.reset();
        mockMvc= MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    void testSaveAndList() throws Exception{
        mockMvc.perform(post("/").param("name", "Dani"));
        mockMvc.perform(post("/").param("name", "Ede"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("employeeList", hasItem(
                        hasProperty("name", equalTo("Ede")))))
                .andExpect(content().string(containsString("Dani")));
    }

}
