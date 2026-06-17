package it.unife.sample.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sepoltura")
public class SepolturaEntity {

    @EmbeddedId
    private SepolturaId id;

    @Column(name = "capacita", nullable = false)
    private Integer capacita;

    @Column(name = "tipologia", nullable = false)
    private String tipologia;

    @Column(name = "stato_di_occupazione", nullable = false)
    private String stato; // "libero" o "occupato" o "occupato parzialmente"
}
