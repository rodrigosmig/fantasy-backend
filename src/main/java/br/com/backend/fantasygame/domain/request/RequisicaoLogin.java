package br.com.backend.fantasygame.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class RequisicaoLogin {
    @NotNull(message = "O e-mail é obrigatório")
    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    @Length(min = 8, message = "O campo senha deve ter no mínimo 8 caracteres")
    private String senha;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
