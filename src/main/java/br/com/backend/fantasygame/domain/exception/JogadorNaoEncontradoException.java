package br.com.backend.fantasygame.domain.exception;

public class JogadorNaoEncontradoException extends RuntimeException {
    
    public JogadorNaoEncontradoException() {
        super("Jogador não encontrado");
    }
}
