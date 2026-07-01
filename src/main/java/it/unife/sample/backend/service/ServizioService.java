package it.unife.sample.backend.service;

import it.unife.sample.backend.model.ServizioEntity;
import it.unife.sample.backend.repository.ServizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServizioService {

    @Autowired
    private ServizioRepository servizioRepository;

    public List<ServizioEntity> findAll() {
        return servizioRepository.findAll();
    }

    public Optional<ServizioEntity> findById(String nome) {
        return servizioRepository.findById(nome);
    }

    public void deleteById(String nome) {
        Optional<ServizioEntity> servizioOpt = servizioRepository.findById(nome);
        if (servizioOpt.isPresent()) {
            ServizioEntity servizio = servizioOpt.get();
            servizioRepository.delete(servizio);
        }
    }

}