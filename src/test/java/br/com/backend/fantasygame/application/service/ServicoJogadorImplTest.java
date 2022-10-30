package br.com.backend.fantasygame.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.domain.service.ServicoJogador;
import br.com.backend.fantasygame.domain.vo.Nome;
import br.com.backend.fantasygame.domain.vo.Pontos;

@SpringBootTest
public class ServicoJogadorImplTest {

    @Mock
    private RepositorioJogador repositorioJogador;
    private ServicoJogador servicoJogador;
    private Jogador jogador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        this.servicoJogador = new ServicoJogadorImpl(repositorioJogador);

        var pais = new Pais(1l, "Brazil", "BR");
        var posicao = new Posicao(1l, new Nome("4-4-2"));

        this.jogador = new Jogador(1l, new Nome("Test"), new Pontos(0), posicao, pais);
    }

    /* @Test
    void deveriaRetornarUmListaDePaises() {
        when(jogadorRepository.findAll()).thenReturn(List.of(this.jogador));

        var players = jogadorService.getAll();

        assertNotNull(players);
        assertEquals(1, players.size());
        assertEquals(Jogador.class, players.get(0).getClass());
    } */

    @Test
    void deveriaRetornarUmaPosicaoQuandoInfomadoUmIdValido() {
        when(repositorioJogador.findById(anyLong())).thenReturn(Optional.of(this.jogador));

        var optional = servicoJogador.findById(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(Jogador.class, optional.get().getClass());
        assertEquals(this.jogador, optional.get());
    }

    @Test
    void naoDeveriaRetornarNenhumaPosicaoQuandoInformadoIDInvalido() {
        when(repositorioJogador.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = servicoJogador.findById(anyLong());

        assertFalse(optional.isPresent());
    }
}
