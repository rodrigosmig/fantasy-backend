package br.com.backend.fantasygame.infrastracture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@Repository
public interface RepositorioUsuarioJpa extends JpaRepository<UserSchema, Long> {
    Optional<UserSchema> findByEmail(String email);
    
    Boolean existsByEmail(String email);
}
