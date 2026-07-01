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
public class PrenotazioneId implements Serializable {
    @Column(name = "id_defunto")
    private String id_defunto;

    @Column(name = "nome_servizio")
    private String nome_servizio;

   
}
