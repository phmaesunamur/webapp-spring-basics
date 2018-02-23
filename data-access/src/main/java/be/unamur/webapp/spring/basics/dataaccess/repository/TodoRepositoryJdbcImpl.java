package be.unamur.webapp.spring.basics.dataaccess.repository;

import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryJdbcImpl implements TodoRepository {

    private JdbcTemplate jdbcTemplate;

    public TodoRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Todo> findByAuthorId(long authorId) {
        return jdbcTemplate.query(
                "SELECT * FROM t_todo t WHERE t.author_id = ? ORDER BY t.done DESC",
                new Object[]{authorId},
                (rs, rowNum) -> new Todo(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getBoolean(4))
        );
    }

    @Override
    public void create(String content, long authorId) {
        jdbcTemplate.update(
                "INSERT INTO t_todo (id, content, author_id) VALUES (nextval('todo_pk_seq'), ?, ?)",
                content,
                authorId
        );
    }

}
