package it.unife.sample.backend.service;

import it.unife.sample.backend.model.SepolturaEntity;
import it.unife.sample.backend.model.SepolturaId;
import it.unife.sample.backend.repository.SepolturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SepolturaService {


    @Autowired
    private SepolturaRepository sepolturaRepository;

    public List<SepolturaEntity> findAll() {
        return sepolturaRepository.findAll();
    }

    public Optional<SepolturaEntity> findById(String id) {
        String settoreSepoltura = id.substring(0, 1);
        String idSepoltura = id.substring(1);
        return sepolturaRepository.findById(new SepolturaId(settoreSepoltura, idSepoltura));
    }

}