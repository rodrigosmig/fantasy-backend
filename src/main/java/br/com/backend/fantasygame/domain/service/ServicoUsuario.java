package br.com.backend.fantasygame.domain.service;

import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.User;

public interface ServicoUsuario {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User create(User form, String nomeTime);
    User getUser();
}
