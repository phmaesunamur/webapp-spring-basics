package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.business.exception.BusinessException;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import be.unamur.webapp.spring.basics.dataaccess.repository.TodoRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void updateStatus_should_throw_exception_when_todo_not_found() {
        expectedException.expect(BusinessException.class);
        final long todoId = 68;

        when(todoRepository.findById(todoId)).thenReturn(null);

        todoService.updateStatus(todoId, false);
    }

    @Test
    public void updateStatus_should_xxx() {
        final long todoId = 68;

        when(todoRepository.findById(todoId)).thenReturn(new Todo(todoId, "fdsfs", 78));

        todoService.updateStatus(todoId, false);

        verify(todoRepository).updateStatus(todoId, false);
    }
}
