package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.DefuntoEntity;
import it.unife.sample.backend.service.DefuntoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/defunti")
public class DefuntoEntityController {

    @Autowired
    private DefuntoEntityService service;

    @GetMapping
    public List<DefuntoEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefuntoEntity> getById(@PathVariable String id) {
        Optional<DefuntoEntity> entity = service.findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DefuntoEntity> create(@Valid @RequestBody DefuntoEntity entity) {
        DefuntoEntity salvato = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefuntoEntity> update(@PathVariable String id, @Valid @RequestBody DefuntoEntity entity) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

   
}