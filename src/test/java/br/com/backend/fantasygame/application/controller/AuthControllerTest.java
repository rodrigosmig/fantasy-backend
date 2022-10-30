package br.com.backend.fantasygame.application.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.backend.fantasygame.domain.request.RequisicaoLogin;
import br.com.backend.fantasygame.application.service.ServicoAuth;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @MockBean
    private ServicoAuth servicoAuth;

    @Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void deveriaFalharAoTentarEntrarComUmUsuarioInvalido() throws Exception {
        URI uri = new URI("/auth/login");

        when(servicoAuth.authenticate(any(UsernamePasswordAuthenticationToken.class), any(AuthenticationManager.class)))
            .thenThrow(new UsernameNotFoundException("Dados inválidos"));

        var form = new RequisicaoLogin("test@email.com", "12345678");

        var json = mapper.writeValueAsString(form);

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isUnauthorized()
            )
            .andExpect(jsonPath("$.message", is("Dados inválidos")));;

        verify(servicoAuth).authenticate(any(UsernamePasswordAuthenticationToken.class), any(AuthenticationManager.class));
    }

    @Test
    void deveriaAutenticarComSucesso() throws Exception {
        URI uri = new URI("/auth/login");

        var token = "token api";

        when(servicoAuth.authenticate(any(UsernamePasswordAuthenticationToken.class), any(AuthenticationManager.class)))
            .thenReturn(token);

        var form = new RequisicaoLogin("test@email.com", "12345678");

        var json = mapper.writeValueAsString(form);

        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                status()
                .isOk()
            )
            .andExpect(jsonPath("$.token", is(token)));;

        verify(servicoAuth).authenticate(any(UsernamePasswordAuthenticationToken.class), any(AuthenticationManager.class));
    }
}
