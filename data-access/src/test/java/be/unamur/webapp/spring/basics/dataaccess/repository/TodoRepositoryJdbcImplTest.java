package be.unamur.webapp.spring.basics.dataaccess.repository;

import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@TestPropertySource(locations = {"classpath:context_test_h2.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {TodoRepositoryJdbcImpl.class})
public class TodoRepositoryJdbcImplTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private TodoRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void findByAuthor_should_not_find_anything_because_the_table_is_empty() {
        assertThat(repository.findByAuthorId(3241L)).isEmpty();
    }

    @Test
    public void create_should_insert_given_todo_and_assign_an_id_automatically() {
        jdbcTemplate.update("INSERT INTO t_author (id, username, password) VALUES (42, 'testauthor', 'pass')");

        repository.create("blabla", 42L);

        List<Todo> todo = repository.findByAuthorId(42L);

        assertThat(todo).hasSize(1);
        Todo todo1 = todo.get(0);
        assertThat(todo1.getId()).isPositive();
        assertThat(todo1.getAuthorId()).isEqualTo(42L);
        assertThat(todo1.getContent()).isEqualTo("blabla");
    }

    @Test
    public void findById_xxx() {
        expectedException.expect(EmptyResultDataAccessException.class);
        repository.findById(46);
    }

    @Test
    public void findById_yyy() {
        jdbcTemplate.update("INSERT INTO t_author (id, username, password) VALUES (42, 'testauthor', 'pass')");
        jdbcTemplate.update("INSERT INTO t_todo (id, content, author_id, done) VALUES (2, 'content', 42, false)");

        final Todo found = repository.findById(2);

        assertThat(found).isNotNull();
    }

    @Test
    public void findById_zzz_mapping() {
        jdbcTemplate.update("INSERT INTO t_author (id, username, password) VALUES (42, 'testauthor', 'pass')");
        jdbcTemplate.update("INSERT INTO t_todo (id, content, author_id, done) VALUES (2, 'content', 42, false)");

        final Todo found = repository.findById(2);

        assertThat(found.getAuthorId()).isEqualTo(42);
        assertThat(found.getContent()).isEqualTo("content");
        assertThat(found.getDone()).isFalse();
    }

    @Test
    public void update_zzz_mapping() {
        jdbcTemplate.update("INSERT INTO t_author (id, username, password) VALUES (42, 'testauthor', 'pass')");
        jdbcTemplate.update("INSERT INTO t_todo (id, content, author_id, done) VALUES (2, 'content', 42, false)");

        repository.updateStatus(2, true);

        final Boolean isDone = jdbcTemplate.queryForObject("SELECT done FROM t_todo WHERE id = 2", Boolean.class);

        assertThat(isDone).isTrue();
    }
}
