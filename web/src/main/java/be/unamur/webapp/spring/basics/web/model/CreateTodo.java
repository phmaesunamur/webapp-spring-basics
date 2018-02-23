package be.unamur.webapp.spring.basics.web.model;

public class CreateTodo {

    private String inputContent;

    public CreateTodo(String inputContent) {
        this.inputContent = inputContent;
    }

    public CreateTodo() {
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(final String inputContent) {
        this.inputContent = inputContent;
    }

}