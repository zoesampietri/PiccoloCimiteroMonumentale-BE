package it.unife.sample.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "servizio")
public class ServizioEntity {

    @Id
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "durata")
    private String durata;

    @Column(name = "cf_organizzatore", length = 16, columnDefinition = "CHAR(16)")
    @Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-B-C-D-E-H-L-M-P-R-S-T-U-V-W-X-Y]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$", 
        message = "Il formato del codice fiscale non è valido")
    private String cf_organizzatore;
}
