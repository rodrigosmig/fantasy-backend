package br.com.backend.fantasygame.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.JogadorSchema;

@Repository
public interface RepositorioJogadorJpa extends JpaRepository<JogadorSchema, Long>,
        JpaSpecificationExecutor<JogadorSchema> {
    
}
