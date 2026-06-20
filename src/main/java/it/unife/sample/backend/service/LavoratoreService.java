package it.unife.sample.backend.service;

import it.unife.sample.backend.dto.LoginResponse;
import it.unife.sample.backend.model.LavoratoreEntity;
import it.unife.sample.backend.repository.LavoratoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LavoratoreService {

    @Autowired
    private LavoratoreRepository repository;

    public LoginResponse trovaAmministratorePerMail(String mail, String password) {
        LavoratoreEntity lavoratore = repository.findAdministratorByMail(mail)
                .orElseThrow(() -> new RuntimeException("Amministratore non trovato con la seguente mail: " + mail));
        if (!lavoratore.getPassword().equals(password)) {
            throw new RuntimeException("Password errata");
        }
        return new LoginResponse(lavoratore.getMail(), lavoratore.getNome(), lavoratore.getRuolo(), lavoratore.getCodiceFiscale());
    }

    public List<LavoratoreEntity> ottieniTuttiILavoratori() {
        return repository.findAll();
    }
}
