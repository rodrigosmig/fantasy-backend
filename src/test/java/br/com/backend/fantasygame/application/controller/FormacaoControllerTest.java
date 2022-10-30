package br.com.backend.fantasygame.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import br.com.backend.fantasygame.domain.service.ServicoFormacao;
import br.com.backend.fantasygame.domain.vo.Nome;

@SpringBootTest
@AutoConfigureMockMvc
public class FormacaoControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ServicoFormacao servicoFormacao;

    private Formacao formacao;

    @BeforeEach
    void setUp() {
        this.formacao = new Formacao(1l, new Nome("4-4-2"));
    }

    @Test
    void deveriaFalharQuandoUsuarioNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/formacoes");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharQuandoMetodoHttpInvalido() throws Exception {
        URI uri = new URI("/formacoes");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isMethodNotAllowed()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaListaDeFormacoes() throws Exception {
        URI uri = new URI("/formacoes");

        when(servicoFormacao.getAll()).thenReturn(List.of(this.formacao, this.formacao));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[0].id", is(this.formacao.getId().intValue())))
            .andExpect(jsonPath("[0].nome", is(this.formacao.getNome())))
            .andExpect(jsonPath("[1].id", is(this.formacao.getId().intValue())))
            .andExpect(jsonPath("[1].nome", is(this.formacao.getNome())));
            
        verify(servicoFormacao).getAll();
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaFormacaoComSucesso() throws Exception {     
        URI uri = new URI("/formacoes/" + this.formacao.getId().intValue());

        when(servicoFormacao.findById(anyLong())).thenReturn(Optional.of(this.formacao));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.formacao.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.formacao.getNome())));
            
        verify(servicoFormacao).findById(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharAoObterUmaFormacaoInexistente() throws Exception {
        URI uri = new URI("/formacoes/123");

        when(servicoFormacao.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isNotFound()
            )
            .andExpect(jsonPath("$.message", is("Formação não encontrada")));
            
        verify(servicoFormacao).findById(anyLong());
    }
}
