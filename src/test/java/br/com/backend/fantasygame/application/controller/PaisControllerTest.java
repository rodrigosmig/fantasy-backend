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

import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.service.ServicoPais;

@SpringBootTest
@AutoConfigureMockMvc
public class PaisControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ServicoPais servicoPais;

    private Pais pais;

    @BeforeEach
    void setUp() {
        this.pais = new Pais(1l, "Test", "Te");
    }

    @Test
    void deveriaFalharQuandoUsuarioNaoEstaAutenticado() throws Exception {
        URI uri = new URI("/paises");

        mockMvc.perform(post(uri))
            .andExpect(
                status()
                .isForbidden()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharQuandoMetodoHttpInvalido() throws Exception {
        URI uri = new URI("/paises");

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isMethodNotAllowed()
            );
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmaListaDePaises() throws Exception {
        URI uri = new URI("/paises");

        when(servicoPais.getAll()).thenReturn(List.of(this.pais, this.pais));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("[0].id", is(this.pais.getId().intValue())))
            .andExpect(jsonPath("[0].nome", is(this.pais.getNome())));
            
        verify(servicoPais).getAll();
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaObterUmPaisComSucesso() throws Exception {     
        URI uri = new URI("/paises/" + this.pais.getId().intValue());

        when(servicoPais.findById(anyLong())).thenReturn(Optional.of(this.pais));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.id", is(this.pais.getId().intValue())))
            .andExpect(jsonPath("$.nome", is(this.pais.getNome())));
            
        verify(servicoPais).findById(anyLong());
    }

    @Test @WithMockUser(username = "test@email.com", password = "12345678")
    void deveriaFalharAoObterUmPaisInexistente() throws Exception {
        URI uri = new URI("/paises/123");

        when(servicoPais.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isNotFound()
            )
            .andExpect(jsonPath("$.message", is("País não encontrado")));
            
        verify(servicoPais).findById(anyLong());
    }
}
