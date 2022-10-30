package br.com.backend.fantasygame.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.service.ServicoToken;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@Service
public class ServicoAuth implements UserDetailsService {

    @Autowired
    private ServicoToken servicoToken;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public String authenticate(UsernamePasswordAuthenticationToken data, AuthenticationManager authManager) {
        try {
            Authentication authentication = authManager.authenticate(data);
            var userSchema = (UserSchema) authentication.getPrincipal();

            return servicoToken.generateToken(userSchema.toUser());
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Dados inválidos");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repositorioUsuario.findByEmail(username);

        if (user.isPresent()) {          
            return new UserSchema(user.get());
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }
}
