package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.PrenotazioneEntity;
import it.unife.sample.backend.model.PrenotazioneId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, String> {
    @Query("SELECT l FROM PrenotazioneEntity l WHERE l.cf_concessionario = :cf_concessionario")
    List<PrenotazioneEntity> findByCfConcessionario(String cf_concessionario);

    @Query("SELECT l FROM PrenotazioneEntity l WHERE l.id = :id")
    Optional<PrenotazioneEntity> findById(PrenotazioneId id);

    @Query("DELETE FROM PrenotazioneEntity l WHERE l.id = :id")
    void deleteById( PrenotazioneId id);

    @Query("SELECT l FROM PrenotazioneEntity l WHERE l.id.nome_servizio = :nome_servizio AND l.data = :data AND l.ora_inizio = :ora_inizio")
    Optional<PrenotazioneEntity> findByServizioAndDataAndOraInizio(String nome_servizio, java.time.LocalDate data, java.time.LocalTime ora_inizio);

}
