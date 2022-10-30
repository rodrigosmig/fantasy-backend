package br.com.backend.fantasygame.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.fantasygame.infrastracture.schema.PosicaoSchema;

@Repository
public interface RepositorioPosicaoJpa extends JpaRepository<PosicaoSchema, Long> {

}
