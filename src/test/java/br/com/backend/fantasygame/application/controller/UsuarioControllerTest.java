package br.com.backend.fantasygame.application.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.backend.fantasygame.domain.request.RequisicaoCadastrarUsuario;
import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.service.ServicoUsuario;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import br.com.backend.fantasygame.domain.vo.Senha;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
    private ServicoUsuario servicoUsuario;
    
    @Autowired
    private ObjectMapper mapper;
    
    private User user;

    @BeforeEach
    void setUp() {
        var time = new Time(1l, new Nome("Test Team"), new Pontos(0), new Formacao(1l, new Nome("4-4-2")), this.user, LocalDateTime.now());
        this.user = new User(1l, new Nome("Test"), new Senha("12345678"), new Email("test@email.com"));
        this.user.setTime(time);
    }

    @Test
    void deveriaFalharQuandoNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/auth/me");

        mockMvc.perform(post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }
    
    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaRetornarOsDadosDoUsuarioComSucesso() throws Exception {
        URI uri = new URI("/users/me");

        when(servicoUsuario.getUser()).thenReturn(this.user);

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(user.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(user.getNome())))
            .andExpect(jsonPath("$.email", is(user.getEmail())))
            .andReturn();

        verify(servicoUsuario).getUser();
    }

    @Test
    void deveriaCadastrarOUsuarioComSucesso() throws Exception {
        when(servicoUsuario.create(any(User.class), anyString())).thenReturn(user);

        URI uri = new URI("/users");

        var form = new RequisicaoCadastrarUsuario("Test", "test@test.com.br", "Test Team", "12345678", "12345678");

        var json = mapper.writeValueAsString(form);

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isCreated()
            )
            .andExpect(jsonPath("$.id", is(user.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(user.getNome())))
            .andExpect(jsonPath("$.email", is(user.getEmail())))
            .andExpect(jsonPath("$.time.nome", is(user.getTime().getNome())));

        verify(servicoUsuario).create(any(User.class), anyString());
    }

    @Test
    void deveriaFalharAoTentarCadastrarOUsuarioComEmailInvalido() throws Exception {
        when(servicoUsuario.create(any(User.class), anyString())).thenReturn(user);

        URI uri = new URI("/users");

        var form = new RequisicaoCadastrarUsuario("Test", "invalid emaild", "Test Team", "12345678", "12345678");

        var json = mapper.writeValueAsString(form);

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isUnprocessableEntity()
            )
            .andExpect(jsonPath("[0].field", is("email")));

        verify(servicoUsuario, times(0)).create(any(User.class), anyString());
    }

    @Test
    void deveriaFalharAoTentarCadastrarOUsuarioSemONomeDoTime() throws Exception {
        when(servicoUsuario.create(any(User.class), anyString())).thenReturn(user);

        URI uri = new URI("/users");

        var form = new RequisicaoCadastrarUsuario("Test", "invalid emaild", "", "12345678", "12345678");

        var json = mapper.writeValueAsString(form);

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isUnprocessableEntity()
            )
            .andExpect(jsonPath("[0].field", is("nomeTime")));

        verify(servicoUsuario, times(0)).create(any(User.class), anyString());
    }
}
