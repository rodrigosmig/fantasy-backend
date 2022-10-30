package br.com.backend.fantasygame.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.TimeSchema;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;


@Repository
public interface RepositorioTimeJpa extends JpaRepository<TimeSchema, Long> {
    TimeSchema findByUser(UserSchema user);

    Boolean existsByNome(String nome);
}
