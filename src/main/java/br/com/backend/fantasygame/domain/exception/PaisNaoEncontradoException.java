package br.com.backend.fantasygame.domain.exception;

public class PaisNaoEncontradoException extends RuntimeException {
    
    public PaisNaoEncontradoException() {
        super("País não encontrado");
    }
}
