package br.com.backend.fantasygame.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.FormacaoSchema;

@Repository
public interface RepositorioFormacaoJpa extends JpaRepository<FormacaoSchema, Long> {

}
