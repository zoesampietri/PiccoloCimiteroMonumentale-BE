package it.unife.sample.backend.service;

import it.unife.sample.backend.model.DefuntoEntity;
import it.unife.sample.backend.model.LavoratoreEntity;
import it.unife.sample.backend.model.SepolturaEntity;
import it.unife.sample.backend.model.SepolturaId;
import it.unife.sample.backend.repository.DefuntoEntityRepository;
import it.unife.sample.backend.repository.LavoratoreRepository;
import it.unife.sample.backend.repository.SepolturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DefuntoEntityService {

    @Autowired
    private DefuntoEntityRepository defuntoRepository;

    @Autowired
    private LavoratoreRepository lavoratoreRepository;

    @Autowired
    private SepolturaRepository sepolturaRepository;

    public List<DefuntoEntity> findAll() {
        return defuntoRepository.findAll();
    }

    public Optional<DefuntoEntity> findById(String id) {
        return defuntoRepository.findById(id);
    }

    @Transactional
    public DefuntoEntity save(DefuntoEntity entity) {
        // Validazione delle date
        if (!validaDate(entity.getDataNascita(), entity.getDataMorte(), entity.getDataSepoltura())) {
            throw new IllegalArgumentException("Le date inserite non sono coerenti");
        }

        // Validazione dell'amministratore responsabile
        String Responsabile = entity.getAmministratoreResponsabile();

        Optional<LavoratoreEntity> lavoratoreOpt = lavoratoreRepository.findById(Responsabile);
        // Se il lavoratore non esiste OPPURE esiste ma non ha il ruolo corretto, lanciamo l'eccezione
        if (lavoratoreOpt.isEmpty() || !lavoratoreOpt.get().getRuolo().equalsIgnoreCase("amministratore")) {
            throw new IllegalArgumentException("L'amministratore responsabile inserito non esiste o non ha il ruolo di amministratore nel database");
        }

        // Validazione della sepoltura
        SepolturaId nuovaSepolturaId = new SepolturaId(entity.getSettoreSepoltura(), entity.getIdSepoltura());
        // Verifichiamo se l'id del defunto è già presente nel DB (siamo in fase di MODIFICA)
        if (entity.getId() != null && defuntoRepository.existsById(entity.getId())) {
            DefuntoEntity vecchioDefunto = defuntoRepository.findById(entity.getId()).get();
            SepolturaId vecchiaSepolturaId = new SepolturaId(vecchioDefunto.getSettoreSepoltura(), vecchioDefunto.getIdSepoltura());
            // Se l'utente ha effettivamente cambiato il posto della sepoltura
            if (!nuovaSepolturaId.equals(vecchiaSepolturaId)) {
                // A. Controlliamo e occupiamo la NUOVA sepoltura
                occupaSepoltura(nuovaSepolturaId);
                // B. Liberiamo la VECCHIA sepoltura
                liberaSepoltura(vecchiaSepolturaId);
            }
        } 
        else {
            // Siamo in fase di CREAZIONE: dobbiamo solo occupare la nuova sepoltura
            occupaSepoltura(nuovaSepolturaId);
        }

        //logica per creare id del defunto
        if (entity.getId() == null || entity.getId().isEmpty()) {
            //trovo id più grande tra id defunti esistenti
            String maxIdDefunto = defuntoRepository.findMaxIdDefunto();
            //incremento di 1 e creo nuovo id
            String idDefunto = String.format("%010d", Integer.parseInt(maxIdDefunto) + 1);
            entity.setId(idDefunto);
        }

        return defuntoRepository.save(entity);
    }

    public void deleteById(String id) {
        Optional<DefuntoEntity> defuntoOpt = defuntoRepository.findById(id);
        if (defuntoOpt.isPresent()) {
            DefuntoEntity defunto = defuntoOpt.get();
            SepolturaId sepolturaId = new SepolturaId(defunto.getSettoreSepoltura(), defunto.getIdSepoltura());
            liberaSepoltura(sepolturaId);
            defuntoRepository.deleteById(id);
        }
    }

    //--- Metodi privati di supporto ---//

    private void occupaSepoltura(SepolturaId id) {
        SepolturaEntity sepoltura = sepolturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La sepoltura specificata non esiste nel database"));
        
        if ("occupato".equalsIgnoreCase(sepoltura.getStato())) {
            throw new IllegalArgumentException("La sepoltura selezionata è già occupata");
        }
        
        int capacita = sepoltura.getCapacita();
        if (capacita == 1) {
            sepoltura.setStato("occupato");
        } else if (capacita > 1) {
            // Conta quanti defunti ci sono già in quella sepoltura nel DB
            long giaPresenti = defuntoRepository.countBySettoreSepolturaAndIdSepoltura(id.getId(), id.getNumero());
            
            // Se aggiungendo questo defunto raggiungiamo la capacità massima
            if (giaPresenti + 1 == capacita) {
                sepoltura.setStato("occupato");
            } else {
                sepoltura.setStato("occupato parzialmente");
            }
        }
        sepolturaRepository.save(sepoltura);
    }

    private void liberaSepoltura(SepolturaId id) {
        sepolturaRepository.findById(id).ifPresent(sepoltura -> {
            int capacita = sepoltura.getCapacita();
            if (capacita == 1) {
                sepoltura.setStato("libero");
            } else if (capacita > 1) {
                long presentiOggi = defuntoRepository.countBySettoreSepolturaAndIdSepoltura(id.getId(), id.getNumero());
                
                // Se stiamo cancellando/rimuovendo il defunto, nel DB c'è ancora. 
                // Quindi rimasti = presentiOggi - 1
                long rimasti = presentiOggi - 1;

                if (rimasti <= 0) {
                    sepoltura.setStato("libero");
                } else {
                    sepoltura.setStato("occupato parzialmente");
                }
            }
            sepolturaRepository.save(sepoltura);
        });
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