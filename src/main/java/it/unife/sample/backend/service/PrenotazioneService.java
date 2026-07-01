package it.unife.sample.backend.service;

import it.unife.sample.backend.model.PrenotazioneEntity;
import it.unife.sample.backend.model.PrenotazioneId;
import it.unife.sample.backend.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ConcessionarioService concessionarioService;

    public List<PrenotazioneEntity> findByCfConcessionario(String cf_concessionario) {
        return prenotazioneRepository.findByCfConcessionario(cf_concessionario);
    }

    public PrenotazioneEntity create(PrenotazioneEntity entity) {
        entity.setStato_organizzativo("Da definire");
        entity.setStato_pagamento("Da saldare");

        if (!(entity.getOra_inizio() == null  || entity.getData() == null)) {
            // controllo che nel database non esista già una prenotazione dello stesso servizio alla stessa data e ora
            Optional<PrenotazioneEntity> existingEntity = prenotazioneRepository.findByServizioAndDataAndOraInizio(entity.getId().getNome_servizio(), entity.getData(), entity.getOra_inizio());
            if (existingEntity.isPresent()) {
                throw new IllegalArgumentException("Esiste già una prenotazione per il servizio " + entity.getId().getNome_servizio() + " alla data " + entity.getData() + " e ora " + entity.getOra_inizio());
            }
            
            // salvo l'entità solo se la data di prenotazione è nel futuro
            if (entity.getData().isAfter(java.time.LocalDate.now())) {
                return prenotazioneRepository.save(entity);
            } else {
                throw new IllegalArgumentException("La data di prenotazione deve essere nel futuro.");
            }
        }

        return prenotazioneRepository.save(entity);
        
        
    }

    public PrenotazioneEntity update(PrenotazioneEntity entity) {
        // cerca nel database l'entità che corrisponde all'id della prenotazione da aggiornare
        Optional<PrenotazioneEntity> existingEntity = prenotazioneRepository.findById(entity.getId());
        if (!existingEntity.isPresent()) {
            throw new IllegalArgumentException("La prenotazione con id " + entity.getId() + " non esiste.");
        }
        // se la data di prenotazionene era nel passato, sto rinnovando quindi devo modificare lo stato organizzativo e di pagamento
        if (existingEntity.get().getData().isBefore(java.time.LocalDate.now())) {
            entity.setStato_organizzativo("Da definire");
            entity.setStato_pagamento("Da saldare");
        }
        else{
            // se la data di prenotazione era nel futuro, sto modificando quindi mantengo lo stato organizzativo e di pagamento
            entity.setStato_organizzativo(existingEntity.get().getStato_organizzativo());
            entity.setStato_pagamento(existingEntity.get().getStato_pagamento());
        }

        // controllo che nel database non esista già una prenotazione dello stesso servizio alla stessa data e ora
        Optional<PrenotazioneEntity> StessoServizio = prenotazioneRepository.findByServizioAndDataAndOraInizio(entity.getId().getNome_servizio(), entity.getData(), entity.getOra_inizio());
        if (StessoServizio.isPresent()) {
            throw new IllegalArgumentException("Esiste già un'altra prenotazione per il servizio " + entity.getId().getNome_servizio() + " alla data " + entity.getData() + " e ora " + entity.getOra_inizio());
        }

        //salvo l'entità finale solo se la data di prenotazione è nel futuro
        if (entity.getData().isAfter(java.time.LocalDate.now())) { 
            return prenotazioneRepository.save(entity);
        } else {
            throw new IllegalArgumentException("La data di prenotazione deve essere nel futuro.");
        }
    }

    public Optional<PrenotazioneEntity> findById(String id) {
        return prenotazioneRepository.findById(id);
    }

    public void deleteById(PrenotazioneId id) {
        prenotazioneRepository.deleteById(id);
    }

    public Optional<PrenotazioneEntity> findById(PrenotazioneId id) {
        return prenotazioneRepository.findById(id);
    }

    public PrenotazioneEntity paga(PrenotazioneEntity entity, String cf_concessionario) {
        //se il concessionario che sta pagando è diverso da quello della prenotazione, no
        if (!entity.getCf_concessionario().equals(cf_concessionario)) {
            throw new IllegalArgumentException("Il concessionario " + cf_concessionario + " non può pagare la prenotazione con id " + entity.getId() + " perché non è il concessionario associato alla prenotazione.");
        }
        
        if (concessionarioService.findByCf(cf_concessionario).getMetodo_pagamento() == null) {
            throw new IllegalArgumentException("Il concessionario " + cf_concessionario + " non può pagare la prenotazione con id " + entity.getId() + " perché non ha un metodo di pagamento associato.");
        }
        
        // aggiorno lo stato di pagamento della prenotazione
        entity.setStato_pagamento("Saldato");
        return prenotazioneRepository.save(entity);
    }
}