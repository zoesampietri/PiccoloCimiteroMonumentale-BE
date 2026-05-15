package it.unife.sample.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDate;
import org.hibernate.annotations.JdbcTypeCode;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "defunto")
public class DefuntoEntity {

    @Id
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Il codice fiscale è obbligatorio")
    @Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-B-C-D-E-H-L-M-P-R-S-T-U-V-W-X-Y]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$", 
        message = "Il formato del codice fiscale non è valido")
    private String codiceFiscale;

    @Column(nullable = false)
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    
    private String luogoNascita;
    @Past(message = "La data di nascita deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    private String luogoMorte;
    @Past(message = "La data di morte deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataMorte;

    @Column(length = 1, nullable = false)
    @Pattern(regexp = "M|F", message = "Il sesso deve essere 'M' o 'F'")
    private String sesso;

    private String statoCivile;
    private String causaDecesso;
    private String indirizzoUltimaResidenza;
    
    @Size(max=1000, message = "L'epitaffio non può superare i 1000 caratteri")
    private String epitaffio;

    @Column(nullable = false)
    @NotBlank(message = "Il luogo di sepoltura è obbligatorio")
    private String settoreSepoltura;
    @Column(nullable = false)
    @NotBlank(message = "Il numero di sepoltura è obbligatorio")
    private String idSepoltura;

    @Past(message = "La data di sepoltura deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSepoltura;

    @Column(nullable = false)
    @NotBlank(message = "L'amministratore responsabile è obbligatorio")
    private String amministratoreResponsabile;

}
