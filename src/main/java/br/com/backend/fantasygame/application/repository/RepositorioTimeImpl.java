package br.com.backend.fantasygame.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.backend.fantasygame.domain.entity.Time;
import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import br.com.backend.fantasygame.infrastracture.repository.RepositorioTimeJpa;
import br.com.backend.fantasygame.infrastracture.schema.TimeSchema;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

@Component
public class RepositorioTimeImpl implements RepositorioTime {

    @Autowired
    private RepositorioTimeJpa repositorioTime;

    @Override
    public Time save(Time time) {
        var timeSchema = repositorioTime.save(new TimeSchema(time));

        return timeSchema.toTime();
    }    

    @Override
    public Optional<Time> findById(Long id) {
        var userSchema = repositorioTime.findById(id);

        return userSchema.map(TimeSchema::toTime);
    }

    @Override
    public Time findByUser(User user) {
        var timeSchema = repositorioTime.findByUser(new UserSchema(user));

        return timeSchema.toTime();
    }

    @Override
    public List<Time> findAll() {
        return repositorioTime.findAll().stream()
            .map(TimeSchema::toTime)
            .toList();
    }

    @Override
    public Boolean existsByNome(String nomeTime) {
        return repositorioTime.existsByNome(nomeTime);
    }
}
