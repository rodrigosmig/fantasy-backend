package br.com.backend.fantasygame.domain.service;

import br.com.backend.fantasygame.domain.entity.User;

public interface ServicoToken {
    String generateToken(User user);
    boolean isValid(String token);
    String getSubject(String token);
}
