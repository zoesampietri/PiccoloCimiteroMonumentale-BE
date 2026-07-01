package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.PrenotazioneEntity;
import it.unife.sample.backend.model.PrenotazioneId;
import it.unife.sample.backend.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    @GetMapping("/{cf_concessionario}")
    public List<PrenotazioneEntity> getPrenotazioneConcessionario(@PathVariable String cf_concessionario) {
        return service.findByCfConcessionario(cf_concessionario);
    }

    @PostMapping
    public PrenotazioneEntity create(@RequestBody PrenotazioneEntity entity) {
        return service.create(entity);
    }

    @PutMapping
    public ResponseEntity<PrenotazioneEntity> update(@RequestBody PrenotazioneEntity entity) {
        Optional<PrenotazioneEntity> existingEntity = service.findById(entity.getId());
        if (!existingEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        PrenotazioneEntity updatedEntity = service.update(entity);
        return ResponseEntity.ok(updatedEntity);
    }
    
    @DeleteMapping("/{nome_servizio}/{id_defunto}")
    public ResponseEntity<Void> delete(@PathVariable String nome_servizio, @PathVariable String id_defunto) {
        PrenotazioneId id = new PrenotazioneId( id_defunto, nome_servizio);
        System.out.println("Deleting prenotazione with id: " + id);
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/paga")
    public ResponseEntity<PrenotazioneEntity> paga(@RequestBody Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        
        // 1. Estrai e mappa l'oggetto "id" interno al JSON
        PrenotazioneId id = mapper.convertValue(payload.get("id"), PrenotazioneId.class);
        
        // 2. Estrai la stringa "cf" (assicurati che nel JSON sia scritto minuscolo "cf")
        String cf = (String) payload.get("cf");
        
        System.out.println("Paga prenotazione con id: " + id + " e cf: " + cf);
        
        // Il resto del tuo codice rimane invariato
        Optional<PrenotazioneEntity> existingEntity = service.findById(id);
        if (!existingEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        PrenotazioneEntity updatedEntity = service.paga(existingEntity.get(), cf);
        return ResponseEntity.ok(updatedEntity);
    }

}