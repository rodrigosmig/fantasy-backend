package br.com.backend.fantasygame.application.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioUsuarioJpa;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@Component
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    @Autowired 
    private RepositorioUsuarioJpa repositorioUsuario;

    @Override
    public Optional<User> findByEmail(String email) {
        var usuarioSchema = repositorioUsuario.findByEmail(email);

        return usuarioSchema.map(UserSchema::toUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        var usuarioSchema = repositorioUsuario.findById(id);

        return usuarioSchema.map(UserSchema::toUser);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repositorioUsuario.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        var usuarioSchema = repositorioUsuario.save(new UserSchema(user));
        return usuarioSchema.toUser();
    }
}
