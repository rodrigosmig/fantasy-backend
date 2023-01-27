package br.com.backend.fantasygame.infrastracture.repository;

import br.com.backend.fantasygame.infrastracture.schema.TimeSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.JogadorSchema;

import java.util.Set;

@Repository
public interface RepositorioJogadorJpa extends JpaRepository<JogadorSchema, Long>,
        JpaSpecificationExecutor<JogadorSchema> {
    Integer countAllByIdIn(Set<Long> idsJogadores);
    Set<JogadorSchema> findByIdIn(Set<Long> ids);
}
