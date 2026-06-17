package it.unife.sample.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SepolturaId implements Serializable {
    @Column(name = "id_sepoltura")
    private String id; // Corrisponde a settoreSepoltura o id del defunto

    @Column(name = "numero")
    private String numero; // Corrisponde a idSepoltura del defunto
}
