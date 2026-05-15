package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.DefuntoEntity;
import it.unife.sample.backend.service.DefuntoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/defunto-entities")
public class DefuntoEntityController {

    @Autowired
    private DefuntoEntityService service;

    @GetMapping
    public List<DefuntoEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefuntoEntity> getById(@PathVariable UUID id) {
        Optional<DefuntoEntity> entity = service.findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DefuntoEntity create(@Valid @RequestBody DefuntoEntity entity) {
        // Prima di salvare l'entità voglio controllarese le date inserite hanno senso (la data di nascita deve essere prima della data di morte, e la data di sepoltura deve essere dopo la data di morte)
        if (validaDate(entity.getDataNascita(), entity.getDataMorte(), entity.getDataSepoltura())) {
            return service.save(entity);
        } else {
            throw new IllegalArgumentException("Le date inserite non sono coerenti");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefuntoEntity> update(@PathVariable UUID id, @Valid @RequestBody DefuntoEntity entity) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        if (!validaDate(entity.getDataNascita(), entity.getDataMorte(), entity.getDataSepoltura())) {
            throw new IllegalArgumentException("Le date inserite non sono coerenti");
        }
        
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean validaDate(LocalDate dataNascita, LocalDate dataMorte, LocalDate dataSepoltura) {
    if (dataNascita != null && dataMorte != null && dataNascita.isAfter(dataMorte)) {
        return false;
    }
    if (dataMorte != null && dataSepoltura != null && dataSepoltura.isBefore(dataMorte)) {
        return false;
    }
    return true;
}
}