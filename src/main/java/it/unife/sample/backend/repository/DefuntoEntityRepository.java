package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.DefuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DefuntoEntityRepository extends JpaRepository<DefuntoEntity, UUID> {
}