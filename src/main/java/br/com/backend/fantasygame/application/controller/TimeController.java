package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.backend.fantasygame.application.dto.TimeDto;
import br.com.backend.fantasygame.domain.exception.TimeNaoEncontradoException;
import br.com.backend.fantasygame.domain.service.ServicoTime;

@RestController
@RequestMapping("/times")
public class TimeController {
    
    @Autowired
    private ServicoTime servicoTime;
    
    @GetMapping
    public ResponseEntity<List<TimeDto>> allTeams() {
        var teams = servicoTime.obterTodosOsTimes();
        
        return ResponseEntity.ok(TimeDto.toList(teams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeDto> show(@PathVariable Long id) {
        var timeOptional = servicoTime.obterTimePorId(id);

        var time = timeOptional.orElseThrow(TimeNaoEncontradoException::new);
        
        return ResponseEntity.ok(new TimeDto(time));
    }
}
