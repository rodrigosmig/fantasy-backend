package br.com.backend.fantasygame.application.service;

import java.util.List;
import java.util.Optional;

import br.com.backend.fantasygame.domain.entity.Pais;
import br.com.backend.fantasygame.domain.repository.RepositorioPais;
import br.com.backend.fantasygame.domain.service.ServicoPais;

public class ServicoPaisImpl implements ServicoPais {

    private final RepositorioPais repositorioPais;

    public ServicoPaisImpl(RepositorioPais repositorioPais) {
        this.repositorioPais = repositorioPais;
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return repositorioPais.findById(id);
    }

    @Override
    public List<Pais> getAll() {
        return repositorioPais.findAll();
    }    
}
