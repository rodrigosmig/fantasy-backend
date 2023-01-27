package br.com.backend.fantasygame.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;

public interface RepositorioTime {
    Optional<Time> findById(Long id);
    Time findByUser(User user);
    List<Time> findAll();
    Boolean existsByNome(String nome);
    Time save(Time time);
}
