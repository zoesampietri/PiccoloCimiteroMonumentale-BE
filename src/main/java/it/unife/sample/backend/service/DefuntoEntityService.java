package it.unife.sample.backend.service;

import it.unife.sample.backend.model.DefuntoEntity;
import it.unife.sample.backend.repository.DefuntoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefuntoEntityService {

    @Autowired
    private DefuntoEntityRepository repository;

    public List<DefuntoEntity> findAll() {
        return repository.findAll();
    }

    public Optional<DefuntoEntity> findById(UUID id) {
        return repository.findById(id);
    }

    public DefuntoEntity save(DefuntoEntity entity) {
        return repository.save(entity);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}