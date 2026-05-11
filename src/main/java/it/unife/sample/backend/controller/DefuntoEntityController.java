package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.DefuntoEntity;
import it.unife.sample.backend.service.DefuntoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public DefuntoEntity create(@RequestBody DefuntoEntity entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefuntoEntity> update(@PathVariable UUID id, @RequestBody DefuntoEntity entity) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
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
}