package be.unamur.webapp.spring.basics.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class WebUser extends User {

    private final long authorId;

    public WebUser(long authorId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.authorId = authorId;
    }

    public long getAuthorId() {
        return authorId;
    }
    
}
