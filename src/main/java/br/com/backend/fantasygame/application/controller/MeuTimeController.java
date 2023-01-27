package br.com.backend.fantasygame.application.controller;

import br.com.backend.fantasygame.domain.request.RequisicaoAlterarTime;
import br.com.backend.fantasygame.application.dto.TimeDTO;
import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.request.RequisicaoSalvarJogadores;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("times/meutime")
public class MeuTimeController {
    @Autowired
    private ServicoTime servicoTime;

    @GetMapping
    public ResponseEntity<TimeDTO> meuTime() {
        Time team = servicoTime.obterTimeDoUsuario();

        return ResponseEntity.ok(new TimeDTO(team));
    }

    @PutMapping
    public ResponseEntity<TimeDTO> alterarMeuTime(@RequestBody @Valid RequisicaoAlterarTime requisicao) {
        Time team = servicoTime.alterarTime(requisicao);

        return ResponseEntity.ok(new TimeDTO(team));
    }

    @PostMapping("/jogadores")
    public ResponseEntity<Integer> salvarJogadores(@RequestBody @Valid RequisicaoSalvarJogadores requisicao) {
        servicoTime.salvarJogadores(requisicao);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
