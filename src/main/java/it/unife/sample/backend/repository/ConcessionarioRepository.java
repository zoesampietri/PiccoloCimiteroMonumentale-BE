package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.ConcessionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ConcessionarioRepository extends JpaRepository<ConcessionarioEntity, String> {

    @Query("SELECT c FROM ConcessionarioEntity c WHERE c.mail = :mail AND c.password = :password")
    Optional<ConcessionarioEntity> findConcessionarioByMailAndPassword(String mail, String password);
}