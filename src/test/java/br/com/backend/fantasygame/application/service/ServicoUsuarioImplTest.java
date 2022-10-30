package br.com.backend.fantasygame.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.backend.fantasygame.domain.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.service.ServicoUsuario;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Senha;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@SpringBootTest
public class ServicoUsuarioImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Authentication auth;
    @Mock
    private RepositorioUsuario repositorioUsuario;
    @Mock
    private ServicoTime servicoTime;

    private ServicoUsuario servicoUsuario;
    private User user;
    
    @BeforeEach
    void setUp() {
        this.servicoUsuario = new ServicoUsuarioImpl(repositorioUsuario, passwordEncoder, servicoTime);

        this.user = new User(1l, new Nome("Test User"), new Senha("test@email.com"), new Email("12345678"));
    }

    @Test
    void deveriaRetornarUmUsuarioQuandoInfomadoUmIdValido() {
        when(repositorioUsuario.findById(anyLong())).thenReturn(Optional.of(this.user));

        var optional = servicoUsuario.findById(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(User.class, optional.get().getClass());
        assertEquals(this.user, optional.get());
    }

    @Test
    void naoDeveriaRetornarNenhumaPosicaoQuandoInformadoIDInvalido() {
        when(repositorioUsuario.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = servicoUsuario.findById(anyLong());

        assertFalse(optional.isPresent());
    }

    @Test
    void deveriaCriarUsuarioComSucesso() {
        when(repositorioUsuario.save(any(User.class)))
            .thenReturn(this.user);

        var newUser = servicoUsuario.create(this.user, anyString());
        
        verify(repositorioUsuario).save(any(User.class));
        verify(servicoTime).criarTime(any(User.class), anyString());
        verify(passwordEncoder).encode(anyString());

        assertNotNull(newUser);
        assertEquals(User.class, newUser.getClass());
        assertEquals(this.user, newUser);
    }

    @Test
    void deveriaRetornarOUsuarioAutenticado() {
        when(auth.getPrincipal()).thenReturn(new UserSchema(this.user));
        when(servicoUsuario.findByEmail(anyString())).thenReturn(Optional.of(this.user));
        SecurityContextHolder.getContext().setAuthentication(auth);

        var user = servicoUsuario.getUser();

        verify(auth).getPrincipal();
        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        assertEquals(this.user.getId(), user.getId());

        SecurityContextHolder.clearContext();
    }

    @Test
    void deveriaLancarExcecaoQuandoNaoEncontrarUsuario() {
        when(auth.getPrincipal()).thenReturn(new UserSchema(this.user));
        when(servicoUsuario.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        SecurityContextHolder.getContext().setAuthentication(auth);

        UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class, () -> servicoUsuario.getUser());

        verify(auth).getPrincipal();

        SecurityContextHolder.clearContext();
    }
}
