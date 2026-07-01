package it.unife.sample.backend.controller;

import it.unife.sample.backend.dto.LoginRequest;
import it.unife.sample.backend.dto.LoginResponse;
import it.unife.sample.backend.model.ConcessionarioEntity;
import it.unife.sample.backend.service.ConcessionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concessionari")
public class ConcessionarioController {

    @Autowired
    private ConcessionarioService concessionarioService;

    // 1. Ottieni il profilo di un singolo concessionario tramite la sua mail

    @PostMapping("/login")
    public ResponseEntity<?> ottieniProfiloConcessionario(@RequestBody LoginRequest loginRequest) {
        String mail = loginRequest.getMail();
        String password = loginRequest.getPassword();
        try {
            LoginResponse loginResponse = concessionarioService.trovaConcessionarioPerMail(mail,password);
            return ResponseEntity.ok(loginResponse);
        } catch (RuntimeException e) {
            System.out.println("DEBUG - Errore nel service: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 2. Ottieni la lista di tutti i concessionari
    @GetMapping
    public ResponseEntity<List<ConcessionarioEntity>> ottieniTutti() {
        List<ConcessionarioEntity> concessionari = concessionarioService.ottieniTuttiIConcessionari();
        return ResponseEntity.ok(concessionari);
    }

}
