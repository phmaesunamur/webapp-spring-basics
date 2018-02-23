package be.unamur.webapp.spring.basics.dataaccess.entity;

import java.util.Objects;

public class Todo {

    private long id;
    private String content;
    private long authorId;
    private boolean done = false;

    public Todo() {
        //NOOP
    }

    public Todo(String content, long authorId) {
        this.content = content;
        this.authorId = authorId;
    }

    public Todo(long id, String content, long authorId) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
    }

    public Todo(final long id, final String content, final long authorId, final boolean done) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public boolean isDone() {
        return done;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Todo todo = (Todo) o;
        return getId() == todo.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
}
