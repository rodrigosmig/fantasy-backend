package br.com.backend.fantasygame.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Senha;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@SpringBootTest
public class ServicoAuthTest {

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicoAuth servicoAuth;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.user = new User(1l, new Nome("Test User"), new Senha("test@email.com"), new Email("12345678"));
    }

    @Test
    void deveriaEncontrarUmUsuarioPeloEmailComSucesso() {
        var userSchema = new UserSchema(this.user);

        when(repositorioUsuario.findByEmail(anyString())).thenReturn(Optional.of(this.user));

        var user = servicoAuth.loadUserByUsername("test@email.com");

        verify(repositorioUsuario).findByEmail(anyString());
        assertNotNull(user);
        assertEquals(UserSchema.class, user.getClass());
        assertEquals(userSchema, user);
    }

    @Test
    void deveriaLancarExcecaoQuandoOUsuarioNaoForEncontrado() {
        String message = "Dados inválidos";
        when(repositorioUsuario.findByEmail(anyString())).thenThrow(new UsernameNotFoundException(message));

        var exception = assertThrows(UsernameNotFoundException.class, () -> servicoAuth.loadUserByUsername("test@email.com"));

        assertEquals(message, exception.getMessage());
    }
}
