package be.unamur.webapp.spring.basics.business.service;

import be.unamur.webapp.spring.basics.business.exception.BusinessException;
import be.unamur.webapp.spring.basics.dataaccess.entity.Todo;
import be.unamur.webapp.spring.basics.dataaccess.repository.TodoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional
    public void addNewTodo(final String content, final long authorId) {
        if (StringUtils.isEmpty(content)) {
            throw new BusinessException("content must not be empty!");
        }

        checkArgument(StringUtils.isNotEmpty(content));
        checkArgument(authorId > 0L);

        todoRepository.create(content, authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Todo> listAllTodos(long authorId) {
        return todoRepository.findByAuthorId(authorId);
    }

    //TODO montrer l'attrape avec le this.transactionalMethod

}
