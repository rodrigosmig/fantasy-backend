package br.com.backend.fantasygame.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.backend.fantasygame.domain.entity.Posicao;
import br.com.backend.fantasygame.domain.repository.RepositorioPosicao;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;
import br.com.backend.fantasygame.domain.vo.Nome;

@SpringBootTest
public class ServicoPosicaoImplTest {

    @Mock
    private RepositorioPosicao repositorioPosicao;
    private ServicoPosicao servicoPosicao;
    private Posicao posicao;

    @BeforeEach
    void setUp() {
        this.servicoPosicao = new ServicoPosicaoImpl(repositorioPosicao);

        this.posicao = new Posicao(1l, new Nome("4-4-2"));
    }

    @Test
    void deveriaRetornarUmaListaDePosicoes() {
        when(repositorioPosicao.findAll()).thenReturn(List.of(this.posicao, this.posicao));

        var positions = this.servicoPosicao.getAll();

        assertNotNull(positions);
        assertEquals(2, positions.size());
        assertEquals(Posicao.class, positions.get(0).getClass());
    }

    @Test
    void deveriaRetornarUmaPosicaoQuandoInfomadoUmIdValido() {
        when(repositorioPosicao.findById(anyLong())).thenReturn(Optional.of(this.posicao));

        var optional = this.servicoPosicao.findById(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(Posicao.class, optional.get().getClass());
        assertEquals(this.posicao, optional.get());
    }

    @Test
    void naoDeveriaRetornarNenhumaPosicaoQuandoInformadoIDInvalido() {
        when(repositorioPosicao.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = this.servicoPosicao.findById(anyLong());

        assertFalse(optional.isPresent());
    }
}
