package br.com.backend.fantasygame.domain.exception;

public class StandardError {
    private String message;    

    public StandardError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
