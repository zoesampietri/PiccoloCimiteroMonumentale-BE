package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.LavoratoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LavoratoreRepository extends JpaRepository<LavoratoreEntity, String> {
}