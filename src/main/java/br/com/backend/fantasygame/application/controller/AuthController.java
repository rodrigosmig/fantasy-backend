package br.com.backend.fantasygame.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.TokenDto;
import br.com.backend.fantasygame.domain.request.RequisicaoLogin;
import br.com.backend.fantasygame.application.service.ServicoAuth;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private ServicoAuth servicoAuth;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid RequisicaoLogin requisicao) {
        String token = servicoAuth.authenticate(requisicao.convert(), authManager);
        
        return ResponseEntity.ok(new TokenDto(token));
    }    
}
