package br.com.backend.fantasygame.application.controller;

import br.com.backend.fantasygame.application.dto.AppDataDTO;
import br.com.backend.fantasygame.domain.service.ServicoFormacao;
import br.com.backend.fantasygame.domain.service.ServicoPais;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configurations")
public class AppDataController {

    @Autowired
    private ServicoPais servicoPais;

    @Autowired
    private ServicoPosicao servicoPosicao;

    @Autowired
    private ServicoFormacao servicoFormacao;

    @GetMapping
    public ResponseEntity<AppDataDTO> list() {
        var countries = servicoPais.getAll();
        var formations = servicoFormacao.getAll();
        var positions = servicoPosicao.getAll();

        return ResponseEntity.ok(new AppDataDTO(countries, positions, formations));
    }
}
