package be.unamur.webapp.spring.basics.web.controller;

import be.unamur.webapp.spring.basics.business.service.TodoService;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(path = "/secured/api/todos")
public class TodoRestController {

    private final TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(path = "/{authorId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('AUTHOR')")
    public List<Todo> listTodos(@PathVariable(value = "authorId") long authorId) {
        return todoService.listAllTodos(authorId);
    }
    
}
