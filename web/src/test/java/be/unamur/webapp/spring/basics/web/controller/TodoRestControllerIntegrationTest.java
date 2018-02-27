package be.unamur.webapp.spring.basics.web.controller;

import be.unamur.webapp.spring.basics.business.service.TodoService;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebSecurityIntegrationTestConfig.class, TodoRestControllerIntegrationTest.TestConfiguration.class})
@WebAppConfiguration
public class TodoRestControllerIntegrationTest {

    @Configuration
    @Import({TodoRestController.class})
    public static class TestConfiguration {

        @Bean
        public TodoService todoService() {
            return Mockito.mock(TodoService.class);
        }

    }

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private TodoService todoService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"AUTHOR"})
    public void test() throws Exception {
        final int authorId = 42;

        final List<Todo> todos = Arrays.asList(
                new Todo(1, "bla", authorId),
                new Todo(2, "gfgs", authorId)
        );

        Mockito.when(todoService.listAllTodos(authorId)).thenReturn(todos);

        mvc.perform(MockMvcRequestBuilders.get("/secured/api/todos/{authorId}", authorId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.*.authorId", containsInAnyOrder(authorId, authorId)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[1].id", is(2)));
    }

}
