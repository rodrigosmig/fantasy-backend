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

import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;
import br.com.backend.fantasygame.domain.vo.Nome;

@SpringBootTest
@AutoConfigureMockMvc
public class PosicaoControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ServicoPosicao servicoPosicao;

    private Posicao posicao;

    @BeforeEach
    void setUp() {
        this.posicao = new Posicao(1l, new Nome("4-4-2"));
    }

    @Test
    void deveriaFalharQuandoUsuarioNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/posicoes");

        mockMvc.perform(post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharQuandoMetodoHttpInvalido() throws Exception {
        URI uri = new URI("/posicoes");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isMethodNotAllowed()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaListaDePosicoes() throws Exception {
        URI uri = new URI("/posicoes");

        when(servicoPosicao.getAll()).thenReturn(List.of(this.posicao, this.posicao));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[0].id", is(this.posicao.getId().intValue())))
            .andExpect(jsonPath("[0].nome", is(this.posicao.getNome())));
            
            verify(servicoPosicao).getAll();
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaPosicaoComSucesso() throws Exception {     
        URI uri = new URI("/posicoes/" + this.posicao.getId().intValue());

        when(servicoPosicao.findById(anyLong())).thenReturn(Optional.of(this.posicao));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.posicao.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.posicao.getNome())));
            
        verify(servicoPosicao).findById(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharAoObterUmaPosicaoInexistente() throws Exception {
        URI uri = new URI("/posicoes/123");

        when(servicoPosicao.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isNotFound()
            )
            .andExpect(jsonPath("$.message", is("Posição não encontrada")));
            
        verify(servicoPosicao).findById(anyLong());
    }
}
