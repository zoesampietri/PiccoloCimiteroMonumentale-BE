package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.SepolturaEntity;
import it.unife.sample.backend.service.SepolturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/sepolture")
public class SepolturaController {

    @Autowired
    private SepolturaService service;

    @GetMapping
    public List<SepolturaEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SepolturaEntity> getById(@PathVariable String id) {
        Optional<SepolturaEntity> entity = service.findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}