package br.com.backend.fantasygame.domain.exception;

public class PosicaoNaoEncontradaException extends RuntimeException {
    
    public PosicaoNaoEncontradaException() {
        super("Posição não encontrada");
    }
}
