package it.unife.sample.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@Entity
@Table(name = "concessionario") // Nome della tabella nel database
public class ConcessionarioEntity {

    @Id
    @Column(name = "cf", length = 16, columnDefinition = "CHAR(16)")
    @NotBlank(message = "Il codice fiscale è obbligatorio")
    @Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-B-C-D-E-H-L-M-P-R-S-T-U-V-W-X-Y]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$", 
        message = "Il formato del codice fiscale non è valido")
    private String cf;

    @Column(name = "nome", nullable = false, length = 45)
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @Column(name = "cognome", nullable = false, length = 45)
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Column(name = "sesso", length = 1, nullable = false)
    @Pattern(regexp = "m|f", message = "Il sesso deve essere 'm' o 'f'")
    private String sesso;

    @Column(name = "luogo_nascita", length = 100)
    private String luogoNascita;

    @Column(name = "data_nascita")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @Column(name = "stato", length = 45)
    private String stato;

    @Column(name = "provincia", length = 2, columnDefinition = "CHAR(2)")
    private String provincia;

    @Column(name = "citta", length = 45)
    private String citta;

    @Column(name = "cap", length = 5, columnDefinition = "CHAR(5)")
    private String cap;

    @Column(name = "via", length = 45)
    private String via;

    @Column(name = "civico", length = 10)
    private String civico;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "mail", nullable = false, length = 45)
    private String mail;

    @Column(name = "metodo_pagamento", length = 45)
    private String metodo_pagamento;

    @Column(name = "password",nullable = false)
    private String password;
}