package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.DefuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DefuntoEntityRepository extends JpaRepository<DefuntoEntity, String> {
    // Aggiungi un query personalizzato per trovare l'id massimo dei defunti
    @Query("SELECT MAX(d.id) FROM DefuntoEntity d")
    String findMaxIdDefunto(); 

    @Query("SELECT COUNT(d) FROM DefuntoEntity d WHERE d.settoreSepoltura = :settoreSepoltura AND d.idSepoltura = :idSepoltura")
    long countBySettoreSepolturaAndIdSepoltura(String settoreSepoltura, String idSepoltura);
}