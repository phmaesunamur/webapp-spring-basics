package be.unamur.webapp.spring.basics.dataaccess.repository;

import be.unamur.webapp.spring.basics.dataaccess.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryJdbcImpl implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepositoryJdbcImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM t_author a WHERE a.username = ?",
                new Object[]{username},
                (rs, rowNum) -> new Author(rs.getLong(1), rs.getString(2), rs.getString(3))
        );
    }

    @Override
    public void create(String username, String password) {
        jdbcTemplate.update(
                "INSERT INTO t_author (id, username, password) VALUES (nextval('author_pk_seq'), ?, ?)",
                username,
                password
        );
    }
    
}
