package be.unamur.webapp.spring.basics.dataaccess.repository;

import be.unamur.webapp.spring.basics.dataaccess.entity.Author;

public interface AuthorRepository {

    Author findByUsername(String username);

    void create(String username, String password);
    
}
