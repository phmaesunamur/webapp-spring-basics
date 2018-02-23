package be.unamur.webapp.spring.basics.web.model;

public class ChangeDoneStatusForTodo {

    private long id;
    private boolean done;

    public ChangeDoneStatusForTodo() {
    }

    public ChangeDoneStatusForTodo(final long id, final boolean done) {
        this.id = id;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

}