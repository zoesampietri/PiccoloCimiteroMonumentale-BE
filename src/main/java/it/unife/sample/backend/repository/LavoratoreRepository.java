package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.LavoratoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LavoratoreRepository extends JpaRepository<LavoratoreEntity, String> {

    @Query("SELECT l FROM LavoratoreEntity l WHERE l.mail = :mail AND l.ruolo = 'Amministratore'")
    Optional<LavoratoreEntity> findAdministratorByMail(String mail);
}