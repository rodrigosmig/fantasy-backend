package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.PosicaoDto;
import br.com.backend.fantasygame.domain.exception.PosicaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;

@RestController
@RequestMapping("/posicoes")
public class PosicaoController {
    
    @Autowired
    private ServicoPosicao servicoPosicao;

    @GetMapping
    public ResponseEntity<List<PosicaoDto>> list() {
        var countries = servicoPosicao.getAll();

        return ResponseEntity.ok(PosicaoDto.convertToList(countries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PosicaoDto> getOne(@PathVariable Long id) {
        var optional = servicoPosicao.findById(id);

        var position = optional.orElseThrow(() -> new PosicaoNaoEncontradaException());
        
        return ResponseEntity.ok(new PosicaoDto(position));
    }
}
