package be.unamur.webapp.spring.basics.dataaccess.repository;

import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;

import java.util.List;

public interface TodoRepository {

    List<Todo> findByAuthorId(long authorId);

    void create(String content, long authorId);

    Todo findById(long todoId);

    void updateStatus(long todoId, boolean status);
}
