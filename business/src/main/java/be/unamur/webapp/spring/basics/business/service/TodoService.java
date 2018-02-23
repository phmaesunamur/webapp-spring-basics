package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;

import java.util.List;

public interface TodoService {

    void addNewTodo(String content, long authorId);

    List<Todo> listAllTodos(long authorId);

}
