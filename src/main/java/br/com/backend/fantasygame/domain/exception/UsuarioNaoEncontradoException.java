package br.com.backend.fantasygame.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado");
    }
}
