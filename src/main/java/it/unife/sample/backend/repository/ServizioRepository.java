package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.ServizioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServizioRepository extends JpaRepository<ServizioEntity, String> {
}
