package it.unife.sample.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "defunto")
public class DefuntoEntity {

    @Id
    @Column(name = "id", length = 10, columnDefinition = "CHAR(10)")
    private String id;

    @Column(name="codice_fiscale",nullable = false, unique = true)
    @NotBlank(message = "Il codice fiscale è obbligatorio")
    @Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-B-C-D-E-H-L-M-P-R-S-T-U-V-W-X-Y]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$", 
        message = "Il formato del codice fiscale non è valido")
    private String codiceFiscale;

    @Column(name="nome",nullable = false)
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @Column(name="cognome",nullable = false)
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Column(name="luogo_nascita")
    private String luogoNascita;

    @Column(name="data_nascita")
    @Past(message = "La data di nascita deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @Column(name="luogo_morte")
    private String luogoMorte;
    @Column(name="data_morte")
    @Past(message = "La data di morte deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataMorte;

    @Column(name="sesso", length = 1, nullable = false)
    @Pattern(regexp = "m|f", message = "Il sesso deve essere 'm' o 'f'")
    private String sesso;

    @Column(name="stato_civile")
    private String statoCivile;
    @Column(name="causa_decesso")
    private String causaDecesso;
    @Column(name="ultima_residenza")
    private String indirizzoUltimaResidenza;

    @Column(name="epitaffio", length = 200)
    @Size(max=200, message = "L'epitaffio non può superare i 200 caratteri")
    private String epitaffio;

    @Column(name="id_settore", nullable = false)
    @NotBlank(message = "Il luogo di sepoltura è obbligatorio")
    private String settoreSepoltura;
    @Column(name="num_sepoltura", nullable = false)
    @NotBlank(message = "Il numero di sepoltura è obbligatorio")
    private String idSepoltura;

    @Column(name="data_sepoltura")
    @Past(message = "La data di sepoltura deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSepoltura;

    @Column(name="cf_amministratore", nullable = false)
    @NotBlank(message = "L'amministratore responsabile è obbligatorio")
    private String amministratoreResponsabile;

}
