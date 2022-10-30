package br.com.backend.fantasygame.domain.repository;

import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.User;

public interface RepositorioUsuario {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Boolean existsByEmail(String email);
    User save(User user);
}
