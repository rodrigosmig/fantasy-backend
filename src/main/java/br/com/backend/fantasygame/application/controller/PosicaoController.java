package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.PosicaoDTO;
import br.com.backend.fantasygame.domain.exception.PosicaoNaoEncontradaException;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;

@RestController
@RequestMapping("/posicoes")
public class PosicaoController {
    
    @Autowired
    private ServicoPosicao servicoPosicao;

    @GetMapping
    public ResponseEntity<List<PosicaoDTO>> list() {
        var positions = servicoPosicao.getAll();

        return ResponseEntity.ok(PosicaoDTO.convertToList(positions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PosicaoDTO> getOne(@PathVariable Long id) {
        var optional = servicoPosicao.findById(id);

        var position = optional.orElseThrow(PosicaoNaoEncontradaException::new);
        
        return ResponseEntity.ok(new PosicaoDTO(position));
    }
}
