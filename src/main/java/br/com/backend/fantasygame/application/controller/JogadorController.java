package br.com.backend.fantasygame.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.JogadorDto;
import br.com.backend.fantasygame.domain.entity.Jogador;
import br.com.backend.fantasygame.domain.exception.JogadorNaoEncontradoException;
import br.com.backend.fantasygame.domain.service.ServicoJogador;

@RestController
@RequestMapping("/jogadores")
public class JogadorController extends BaseController<JogadorDto> {

    @Autowired
    private ServicoJogador servicoJogador;

    @GetMapping
    public ResponseEntity<Page<JogadorDto>> list(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        var players = servicoJogador.getAll(pageable.getPageNumber(), pageable.getPageSize());

        return paginarResposta(JogadorDto.toList(players), pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorDto> show(@PathVariable Long id) {
        var optional = servicoJogador.findById(id);

        var jogador = optional.orElseThrow(JogadorNaoEncontradoException::new);

        return ResponseEntity.ok(new JogadorDto(jogador));
    }


}
