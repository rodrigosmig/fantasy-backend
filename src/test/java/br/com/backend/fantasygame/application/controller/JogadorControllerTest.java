package br.com.backend.fantasygame.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
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

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.service.ServicoJogador;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;

@SpringBootTest
@AutoConfigureMockMvc
public class JogadorControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ServicoJogador servicoJogador;

    private Jogador jogador;

    @BeforeEach
    void setUp() {
        var pais = new Pais(1l, "Brazil", "BR");
        var posicao = new Posicao(1l, new Nome("4-4-2"));

        this.jogador = new Jogador(1l, new Nome("Test"), new Pontos(0), posicao, pais);
    }

    @Test
    void deveriaFalharQuandoUsuarioNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/jogadores");

        mockMvc.perform(MockMvcRequestBuilders
        .post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharQuandoMetodoHttpInvalido() throws Exception {
        URI uri = new URI("/jogadores");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isMethodNotAllowed()
            );
    }

    /* @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaListaDeJogadores() throws Exception {
        URI uri = new URI("/jogadores");

        when(jogadorService.getAll()).thenReturn(List.of(this.jogador, this.jogador));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[0].id", is(this.jogador.getId().intValue())))
            .andExpect(jsonPath("[0].nome", is(this.jogador.getNome())));
            
            verify(jogadorService).getAll();
    } */

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmJogadorComSucesso() throws Exception {     
        URI uri = new URI("/jogadores/" + this.jogador.getId().intValue());

        when(servicoJogador.findById(anyLong())).thenReturn(Optional.of(this.jogador));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.jogador.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.jogador.getNome())))
            .andExpect(jsonPath("$.posicao.nome", is(this.jogador.getPosicao().getNome())))
            .andExpect(jsonPath("$.pais.nome", is(this.jogador.getPais().getNome())));
            
        verify(servicoJogador).findById(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharAoObterUmJogadorInexistente() throws Exception {
        URI uri = new URI("/jogadores/123");

        when(servicoJogador.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isNotFound()
            )
            .andExpect(jsonPath("$.message", is("Jogador não encontrado")));
            
            verify(servicoJogador).findById(anyLong());
    }
}
