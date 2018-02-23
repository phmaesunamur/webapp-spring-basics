package be.unamur.webapp.spring.basics.dataaccess.entity;

import java.util.Objects;

public class Author {

    private long id;
    private String username;
    private String password;

    public Author(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Author author = (Author) o;
        return getId() == author.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
