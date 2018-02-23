package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.dataaccess.entity.Author;
import be.unamur.webapp.spring.basics.dataaccess.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author find(String username) {
        return authorRepository.findByUsername(username);
    }

}
