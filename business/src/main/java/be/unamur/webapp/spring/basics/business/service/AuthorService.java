package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.dataaccess.entity.Author;

public interface AuthorService {

    Author find(String username);

}
