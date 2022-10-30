package br.com.backend.fantasygame.application.service;

import java.util.Optional;

import br.com.backend.fantasygame.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.service.ServicoUsuario;
import br.com.backend.fantasygame.domain.vo.Senha;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;
import org.springframework.transaction.annotation.Transactional;

public class ServicoUsuarioImpl implements ServicoUsuario {

    private final RepositorioUsuario repositorioUsuario;

    private final PasswordEncoder passwordEncoder;

    private final ServicoTime servicoTime;

    public ServicoUsuarioImpl(RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder, ServicoTime servicoTime) {
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = passwordEncoder;
        this.servicoTime = servicoTime;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repositorioUsuario.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repositorioUsuario.findById(id);
    }

    @Transactional
    @Override
    public User create(User user, String nomeTime) {
        user.setSenha(new Senha(passwordEncoder.encode(user.getSenha())));

        var newUser = repositorioUsuario.save(user);

        servicoTime.criarTime(newUser, nomeTime);

        return newUser;
    }

    @Override
    public User getUser() {
        var userSchema = (UserSchema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return this.findByEmail(userSchema.getEmail()).orElseThrow(UsuarioNaoEncontradoException::new);
    }
}
