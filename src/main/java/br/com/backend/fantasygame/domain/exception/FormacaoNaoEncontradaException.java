package br.com.backend.fantasygame.domain.exception;

public class FormacaoNaoEncontradaException extends RuntimeException {
    
    public FormacaoNaoEncontradaException() {
        super("Formação não encontrada");
    }
}
