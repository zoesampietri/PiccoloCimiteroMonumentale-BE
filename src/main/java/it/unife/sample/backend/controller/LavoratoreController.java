package it.unife.sample.backend.controller;

import it.unife.sample.backend.dto.LoginRequest;
import it.unife.sample.backend.dto.LoginResponse;
import it.unife.sample.backend.model.LavoratoreEntity;
import it.unife.sample.backend.service.LavoratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lavoratori")
public class LavoratoreController {

    @Autowired
    private LavoratoreService lavoratoreService;

    // 1. Ottieni il profilo di un singolo lavoratore tramite la sua mail

    @PostMapping("/amministratore")
    public ResponseEntity<?> ottieniProfiloAmministratore(@RequestBody LoginRequest loginRequest) {
        String mail = loginRequest.getMail();
        String password = loginRequest.getPassword();
        System.out.println("DEBUG - Mail ricevuta dal frontend: [" + mail + "]");
        try {
            LoginResponse loginResponse = lavoratoreService.trovaAmministratorePerMail(mail,password);
            return ResponseEntity.ok(loginResponse);
        } catch (RuntimeException e) {
            System.out.println("DEBUG - Errore nel service: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 2. Ottieni la lista di tutti i lavoratori
    @GetMapping
    public ResponseEntity<List<LavoratoreEntity>> ottieniTutti() {
        List<LavoratoreEntity> lavoratori = lavoratoreService.ottieniTuttiILavoratori();
        return ResponseEntity.ok(lavoratori);
    }

}
