package be.unamur.webapp.spring.basics.web.controller;

import be.unamur.webapp.spring.basics.business.service.TodoService;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import be.unamur.webapp.spring.basics.web.model.ChangeDoneStatusForTodo;
import be.unamur.webapp.spring.basics.web.model.CreateTodo;
import be.unamur.webapp.spring.basics.web.security.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/secured/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
    @PreAuthorize("hasRole('AUTHOR')")
    public ModelAndView serveTodoViewForAuthor(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        final ModelAndView modelAndView = new ModelAndView("secured/todos");

        final WebUser webUser = (WebUser) authentication.getPrincipal();

        final List<Todo> todos = todoService.listAllTodos(webUser.getAuthorId());

        modelAndView.addObject("todos", todos);
        modelAndView.addObject("addTodo", new CreateTodo());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('AUTHOR')")
    public ModelAndView addTodo(@ModelAttribute CreateTodo createTodo, Authentication authentication) {
        final WebUser webUser = (WebUser) authentication.getPrincipal();

        todoService.addNewTodo(createTodo.getInputContent(), webUser.getAuthorId());

        return new ModelAndView("redirect:/secured/todos");
    }

    @RequestMapping(path = "/done", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('AUTHOR')")
    @ResponseBody
    public void updateStatus(@RequestBody ChangeDoneStatusForTodo changeDoneStatusForTodo, Authentication authentication) {
        todoService.updateStatus(changeDoneStatusForTodo.getId(), changeDoneStatusForTodo.isDone());
    }

}
