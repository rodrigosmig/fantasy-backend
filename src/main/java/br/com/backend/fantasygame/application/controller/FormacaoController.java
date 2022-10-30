package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.FormacaoDto;
import br.com.backend.fantasygame.domain.entity.Formacao;
import br.com.backend.fantasygame.domain.exception.FormacaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.service.ServicoFormacao;

@RestController
@RequestMapping("/formacoes")
public class FormacaoController {
    
    @Autowired
    private ServicoFormacao servicoFormacao;

    @GetMapping
    public ResponseEntity<List<FormacaoDto>> list() {
        var formations = servicoFormacao.getAll();

        return ResponseEntity.ok(FormacaoDto.ToList(formations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormacaoDto> show(@PathVariable Long id) {
        var optional = servicoFormacao.findById(id);

        Formacao formation = optional.orElseThrow(FormacaoNaoEncontradaException::new);

        return ResponseEntity.ok(new FormacaoDto(formation));
    }
}
