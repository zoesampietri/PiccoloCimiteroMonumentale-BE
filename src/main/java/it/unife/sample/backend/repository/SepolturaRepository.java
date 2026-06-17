package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.SepolturaEntity;
import it.unife.sample.backend.model.SepolturaId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SepolturaRepository extends JpaRepository<SepolturaEntity, SepolturaId> {
}
