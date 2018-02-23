package be.unamur.webapp.spring.basics.business.service.integration;

import be.unamur.webapp.spring.basics.business.config.BusinessConfiguration;
import be.unamur.webapp.spring.basics.business.service.TodoService;
import be.unamur.webapp.spring.basics.dataaccess.config.DataAccessConfiguration;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import be.unamur.webapp.spring.basics.dataaccess.repository.AuthorRepository;
import be.unamur.webapp.spring.basics.dataaccess.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {BusinessConfiguration.class, DataAccessConfiguration.class})
public class TodoServiceIntegrationTest {

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Commit
    public void create() {
        jdbcTemplate.update("INSERT INTO t_author (id, username, password) VALUES (42, 'testauthor', 'pass')");

        final Todo toInsert = new Todo("a lot of things", 42L);
        todoService.addNewTodo(toInsert.getContent(), toInsert.getAuthorId());

        final List<Todo> todo = todoRepository.findByAuthorId(toInsert.getAuthorId());

        assertThat(todo).hasSize(1);
        assertThat(todo.get(0).getId()).isPositive();
        assertThat(todo.get(0).getAuthorId()).isEqualTo(toInsert.getAuthorId());
        assertThat(todo.get(0).getContent()).isEqualTo(toInsert.getContent());
    }

}
