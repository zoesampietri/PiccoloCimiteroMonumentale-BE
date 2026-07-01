package it.unife.sample.backend.service;

import it.unife.sample.backend.dto.LoginResponse;
import it.unife.sample.backend.model.ConcessionarioEntity;
import it.unife.sample.backend.repository.ConcessionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionarioService {

    @Autowired
    private ConcessionarioRepository repository;

    public LoginResponse trovaConcessionarioPerMail(String mail, String password) {
        ConcessionarioEntity concessionario = repository.findConcessionarioByMailAndPassword(mail, password)
                .orElseThrow(() -> new RuntimeException("Concessionario non trovato con la seguente mail: " + mail));
        return new LoginResponse(concessionario.getMail(), concessionario.getNome(),  concessionario.getCognome(),"concessionario", concessionario.getCf());
    }

    public List<ConcessionarioEntity> ottieniTuttiIConcessionari() {
        return repository.findAll();
    }

    public ConcessionarioEntity findByCf(String cf) {
        return repository.findById(cf)
                .orElseThrow(() -> new RuntimeException("Concessionario non trovato con il seguente codice fiscale: " + cf));
    }
}
