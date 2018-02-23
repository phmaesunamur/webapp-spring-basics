package be.unamur.webapp.spring.basics.web.security;

import be.unamur.webapp.spring.basics.business.service.AuthorService;
import be.unamur.webapp.spring.basics.dataaccess.entity.Author;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = getLogger(CustomUserDetailsService.class);

    private final AuthorService authorService;

    @Autowired
    public CustomUserDetailsService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Author author = authorService.find(username);

        if (Objects.isNull(author)) {
            final UsernameNotFoundException usernameNotFoundException =
                    new UsernameNotFoundException("Username " + username + " not found!");
            LOGGER.error("Error during authentication", usernameNotFoundException);
            throw usernameNotFoundException;
        } else {
            return new WebUser(
                    author.getId(),
                    author.getUsername(),
                    new BCryptPasswordEncoder().encode(author.getPassword()),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_AUTHOR"))
            );
        }
    }

}
