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

import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.domain.service.ServicoFormacao;
import br.com.backend.fantasygame.domain.vo.Nome;

@SpringBootTest
public class ServicoFormacaoImplTest {

    @Mock
    private RepositorioFormacao repositorioFormacao;
    private ServicoFormacao servicoFormacao;
    private Formacao formacao;

    @BeforeEach
    void setUp() {
        this.servicoFormacao = new ServicoFormacaoImpl(repositorioFormacao);

        this.formacao = new Formacao(1l, new Nome("4-4-2"));
    }

    @Test
    void deveriaRetornarUmListaDeFormacoes() {
        when(repositorioFormacao.findAll()).thenReturn(List.of(this.formacao, this.formacao));

        var formations = servicoFormacao.getAll();

        assertNotNull(formations);
        assertEquals(2, formations.size());
        assertEquals(Formacao.class, formations.get(0).getClass());
    }

    @Test
    void deveriaRetornarUmaFormacaoQuandoInfomadoUmIdValido() {
        when(repositorioFormacao.findById(anyLong())).thenReturn(Optional.of(this.formacao));

        var optional = servicoFormacao.findById(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(Formacao.class, optional.get().getClass());
        assertEquals(this.formacao, optional.get());
    }

    @Test
    void whenFindByIdDoesNotReturnAnyFormation() {
        when(repositorioFormacao.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = servicoFormacao.findById(anyLong());

        assertFalse(optional.isPresent());
    }
}
