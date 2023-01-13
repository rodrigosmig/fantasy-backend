package br.com.backend.fantasygame.application.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    private final String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
