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
import org.mockito.MockitoAnnotations;

import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.repository.RepositorioPais;
import br.com.backend.fantasygame.domain.service.ServicoPais;

public class ServicoPaisImplTest {
    
    @Mock
    private RepositorioPais repositorioPais;
    private ServicoPais servicoPais;
    private Pais pais;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.servicoPais = new ServicoPaisImpl(repositorioPais);

        this.pais = new Pais(1l, "Test", "Te");
    }

    @Test
    void deveriaRetornarUmaListaDePaises() {
        when(repositorioPais.findAll()).thenReturn(List.of(this.pais, this.pais));

        var countries = servicoPais.getAll();

        assertNotNull(countries);
        assertEquals(2, countries.size());
        assertEquals(Pais.class, countries.get(0).getClass());
    }

    @Test
    void deveriaRetornarUmaPaisQuandoInfomadoUmIdValido() {
        when(repositorioPais.findById(anyLong())).thenReturn(Optional.of(this.pais));

        var optional = servicoPais.findById(anyLong());

        assertTrue(optional.isPresent());
        assertEquals(Pais.class, optional.get().getClass());
        assertEquals(this.pais, optional.get());
    }

    @Test
    void naoDeveriaRetornarNenhumPaisQuandoInformadoIDInvalido() {
        when(repositorioPais.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        var optional = servicoPais.findById(anyLong());

        assertFalse(optional.isPresent());
    }
}
