package br.com.backend.fantasygame.domain.exception;

public class TimeNaoEncontradoException extends RuntimeException {
    
    public TimeNaoEncontradoException() {
        super("Time não encontrado");
    }
}
