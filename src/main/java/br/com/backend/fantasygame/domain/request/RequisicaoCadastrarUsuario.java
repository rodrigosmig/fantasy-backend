package br.com.backend.fantasygame.domain.request;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Senha;
import br.com.backend.fantasygame.validator.UniqueEmail;
import br.com.backend.fantasygame.validator.UniqueTeam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class RequisicaoCadastrarUsuario {
    @Length(min = 3, message = "O nome deve ter mais de três caracteres")
    private String nome;

    @javax.validation.constraints.Email(message = "E-mail inválido")
    @NotBlank(message = "O email é obrigatório")
    @UniqueEmail(message = "O email informado já está em uso")
    private String email;

    @Length(min = 3, message = "O nome do time deve ter mais de três caracteres")
    @NotBlank(message = "O nome do time é obrigatório")
    @UniqueTeam(message = "O nome do time informado já está em uso")
    private String nomeTime;

    @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @NotBlank(message = "O senha é obrigatória")
    private String senha;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    private String confirmacaoSenha;

    public RequisicaoCadastrarUsuario(String nome, String email, String nomeTime, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.email = email;
        this.nomeTime = nomeTime;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public String getSenha() {
        return senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public User ToUser() {
        return new User(null, new Nome(nome), new Senha(senha), new Email(email));
    }
}
