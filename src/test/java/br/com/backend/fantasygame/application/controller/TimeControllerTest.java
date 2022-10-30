package br.com.backend.fantasygame.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.vo.Email;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;
import br.com.backend.fantasygame.domain.vo.Senha;

@SpringBootTest
@AutoConfigureMockMvc
public class TimeControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ServicoTime servicoTime;

    private Time time;

    @BeforeEach
    void setUp() {
        var user = new User(1l, new Nome("Test"), new Senha("12345678"), new Email("test@email.com"));
        this.time = new Time(1l, new Nome("Test Team"), new Pontos(0), new Formacao(1l, new Nome("4-4-2")), user);
    }

    @Test
    void deveriaFalharQuandoUsuarioNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/times");

        mockMvc.perform(post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharQuandoMetodoHttpInvalido() throws Exception {
        URI uri = new URI("/times");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isMethodNotAllowed()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaListaDeTimes() throws Exception {
        URI uri = new URI("/times");

        when(servicoTime.obterTodosOsTimes()).thenReturn(List.of(this.time, this.time));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[0].id", is(this.time.getId().intValue())))
            .andExpect(jsonPath("[0].nome", is(this.time.getNome())))
            .andExpect(jsonPath("[0].formacao.nome", is(this.time.getFormacao().getNome())));
            
        verify(servicoTime).obterTodosOsTimes();
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmTimeComSucesso() throws Exception {     
        URI uri = new URI("/times/" + this.time.getId().intValue());

        when(servicoTime.obterTimePorId(anyLong())).thenReturn(Optional.of(this.time));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.time.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.time.getNome())))
            .andExpect(jsonPath("$.pontos", is(this.time.getPontos())))
            .andExpect(jsonPath("$.formacao.nome", is(this.time.getFormacao().getNome())));
            
        verify(servicoTime).obterTimePorId(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharAoObterUmTimeInexistente() throws Exception {
        URI uri = new URI("/times/123");

        when(servicoTime.obterTimePorId(anyLong())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isNotFound()
            )
            .andExpect(jsonPath("$.message", is("Time não encontrado")));
            
        verify(servicoTime).obterTimePorId(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmTimeDoUsuarioAutenticado() throws Exception {
        URI uri = new URI("/times/meutime");

        when(servicoTime.obterTimeDoUsuario()).thenReturn(this.time);

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.time.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.time.getNome())))
            .andExpect(jsonPath("$.pontos", is(this.time.getPontos())))
            .andExpect(jsonPath("$.formacao.nome", is(this.time.getFormacao().getNome())));
            
        verify(servicoTime).obterTimeDoUsuario();
    }
}
