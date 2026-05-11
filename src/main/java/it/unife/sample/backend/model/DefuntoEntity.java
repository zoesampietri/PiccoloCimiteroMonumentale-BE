package it.unife.sample.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

@Data
@Entity
@Table(name = "defunto")
public class DefuntoEntity {

    @Id
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String luogoNascita;
    private String dataNascita;
    private String luogoMorte;
    private String dataMorte;
    private char sesso;
    private String statoCivile;
    private String causaDecesso;
    private String indirizzoUltimaResidenza;
    private String epitaffio;
    private String settoreSepoltura;
    private String idSepoltura;
    private String dataSepoltura;
    private String amministratoreResponsabile;

}
