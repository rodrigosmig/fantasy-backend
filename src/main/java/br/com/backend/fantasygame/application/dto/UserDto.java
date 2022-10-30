package br.com.backend.fantasygame.application.dto;

import br.com.backend.fantasygame.domain.entity.User;

public class UserDto {
    private Long id;
    private String nome;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public UserDto(Long id, String nome, String email, TimeDto time) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
