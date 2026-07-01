package it.unife.sample.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "prenotazione")
public class PrenotazioneEntity {
    @EmbeddedId
    private PrenotazioneId id;

    @Column(name = "cf_concessionario", length = 16, columnDefinition = "CHAR(16)")
    @Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-B-C-D-E-H-L-M-P-R-S-T-U-V-W-X-Y]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$", 
        message = "Il formato del codice fiscale non è valido")
    private String cf_concessionario;

    @Column(name="data")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @Column(name = "ora_inizio")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime ora_inizio;

    @Column(name = "stato_organizzativo")
    private String stato_organizzativo;

    @Column(name = "stato_pagamento")
    private String stato_pagamento;
}
