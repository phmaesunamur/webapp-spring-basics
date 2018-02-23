package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.dataaccess.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    public void create_should_call_todo_repository() {
        final ArgumentCaptor<String> contentArgumentCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<Long> authorIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        todoService.addNewTodo("content", 42L);

        Mockito.verify(todoRepository).create(contentArgumentCaptor.capture(), authorIdArgumentCaptor.capture());

        assertThat(contentArgumentCaptor.getValue()).isEqualTo("content");
        assertThat(authorIdArgumentCaptor.getValue()).isEqualTo(42L);
    }

}
