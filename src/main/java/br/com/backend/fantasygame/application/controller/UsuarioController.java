package br.com.backend.fantasygame.application.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.fantasygame.application.dto.UserDto;
import br.com.backend.fantasygame.domain.request.RequisicaoCadastrarUsuario;
import br.com.backend.fantasygame.domain.service.ServicoUsuario;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private ServicoUsuario servicoUsuario;
    
    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> register(@RequestBody @Valid RequisicaoCadastrarUsuario form) {
        var user = servicoUsuario.create(form.ToUser(), form.getNomeTime());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var user = servicoUsuario.getUser();

        return ResponseEntity.ok(new UserDto(user));
    }
}
