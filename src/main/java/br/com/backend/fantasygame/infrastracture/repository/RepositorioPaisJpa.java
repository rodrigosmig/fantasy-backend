package br.com.backend.fantasygame.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.PaisSchema;

@Repository
public interface RepositorioPaisJpa extends JpaRepository<PaisSchema, Long> {
    
}
