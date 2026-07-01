package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.ServizioEntity;
import it.unife.sample.backend.service.ServizioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/servizi")
public class ServizioController {

    @Autowired
    private ServizioService service;

    @GetMapping
    public List<ServizioEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ServizioEntity> getById(@PathVariable String nome) {
        Optional<ServizioEntity> entity = service.findById(nome);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> delete(@PathVariable String nome) {
        if (!service.findById(nome).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(nome);
        return ResponseEntity.noContent().build();
    }

   
}