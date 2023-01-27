package br.com.backend.fantasygame.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import br.com.backend.fantasygame.domain.vo.Senha;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@SpringBootTest
public class ServicoTimeImplTest {

    @Mock
    private RepositorioTime repositorioTime;
    @Mock
    private RepositorioFormacao repositorioFormacao;
    @Mock
    private RepositorioJogador repositorioJogador;
    private ServicoTime servicoTime;
    private User user;
    private Time time;

    @BeforeEach
    void setUp() {
        this.servicoTime = new ServicoTimeImpl(repositorioTime, repositorioFormacao, repositorioJogador);

        this.user = new User(1l, new Nome("Test"), new Senha("12345678"), new Email("test@email.com"));
        this.time = new Time(1l, new Nome("Test Team"), new Pontos(0), new Formacao(1l, new Nome("4-4-2")), this.user);
    }

    @Test
    void deveriaRetornarUmUsuarioValido() {
        when(repositorioTime.findById(anyLong())).thenReturn(Optional.of(this.time));

        var optional = servicoTime.obterTimePorId(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(Time.class, optional.get().getClass());
        assertEquals(this.time, optional.get());
    }

    @Test
    void naoDeveriaRetornarNenhumUsuario() {
        when(repositorioTime.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = servicoTime.obterTimePorId(anyLong());

        verify(repositorioTime).findById(anyLong());
        assertFalse(optional.isPresent());
    }

    @Test
    void deveriaCriarOTimeComSucesso() {
        var formacaoTest = new Formacao(1l, new Nome("4-3-3"));
        when(repositorioFormacao.findById(anyLong())).thenReturn(Optional.of(formacaoTest));
        when(repositorioTime.save(any(Time.class))).thenReturn(this.time);

        var newTeam = servicoTime.criarTime(this.user, "TestTeam");

        verify(repositorioTime).save(any(Time.class));
        assertEquals(this.time, newTeam);
        assertEquals(this.time.getFormacao(), formacaoTest);
    }

    @Test
    void deveriaRetornarOTimeDoUsuarioAutenticado() {
        var auth = Mockito.mock(Authentication.class);

        when(auth.getPrincipal()).thenReturn(new UserSchema(this.user));
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(repositorioTime.findByUser(any(User.class))).thenReturn(this.time);

        var time = servicoTime.obterTimeDoUsuario();

        assertEquals(this.time, time);
    }

    @Test
    void DeveriaRetornarUmaListaDeTimes() {
        when(repositorioTime.findAll()).thenReturn(List.of(this.time, this.time, this.time));

        var times = servicoTime.obterTodosOsTimes();

        assertEquals(3, times.size());
    }
}
