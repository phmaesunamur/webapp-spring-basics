package be.unamur.webapp.spring.basics.web.controller;

import be.unamur.webapp.spring.basics.business.service.TodoService;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import be.unamur.webapp.spring.basics.web.model.ChangeDoneStatusForTodo;
import be.unamur.webapp.spring.basics.web.security.WebUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    public void serveTodoViewForAuthor_should_return_todos_view_and_give_the_todos_of_connected_user() {
        final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        final MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new WebUser(42, "username", "password", Collections.emptyList()),
                null
        );

        final List<Todo> todos = Arrays.asList(
                new Todo(1, "bla", 2),
                new Todo(2, "gfgs", 2),
                new Todo(3, "gdfhgh", 3)
        );

        Mockito.when(todoService.listAllTodos(42)).thenReturn(todos);

        final ModelAndView modelAndView = todoController.serveTodoViewForAuthor(authentication, mockHttpServletRequest, mockHttpServletResponse);

        ModelAndViewAssert.assertViewName(modelAndView, "secured/todos");
        ModelAndViewAssert.assertModelAttributeValue(modelAndView, "todos", todos);
    }

    @Test
    public void updateStatus_should_call_todoService() {
        final long todoId = 43;
        final boolean status = false;
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new WebUser(42, "username", "password", Collections.emptyList()),
                null
        );

        todoController.updateStatus(new ChangeDoneStatusForTodo(todoId, status), authentication);

        Mockito.verify(todoService).updateStatus(todoId, false);
    }

}
