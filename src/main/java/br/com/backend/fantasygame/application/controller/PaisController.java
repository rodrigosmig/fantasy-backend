package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.PaisDTO;
import br.com.backend.fantasygame.domain.exception.PaisNaoEncontradoException;
import br.com.backend.fantasygame.domain.service.ServicoPais;

@RestController
@RequestMapping("/paises")
public class PaisController {
    
    @Autowired
    private ServicoPais servicoPais;
    
    @GetMapping
    public ResponseEntity<List<PaisDTO>> list() {
        var countries = servicoPais.getAll();

        return ResponseEntity.ok(PaisDTO.convertToList(countries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> getOne(@PathVariable Long id) {
        var optional = servicoPais.findById(id);

        var country = optional.orElseThrow(PaisNaoEncontradoException::new);
        
        return ResponseEntity.ok(new PaisDTO(country));
    }
}
