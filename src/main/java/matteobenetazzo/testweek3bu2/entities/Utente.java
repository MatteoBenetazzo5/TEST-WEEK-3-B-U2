package matteobenetazzo.testweek3bu2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utente {

    // id univoco dell'utente
    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    // email per il login
    @Column(nullable = false, unique = true)
    private String email;

    // password criptata
    @Column(nullable = false)
    private String password;

    // enum salvato come STRINGA nel DB
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoloUtente ruolo;

    // data creazione utente
    @Column(name = "data_creazione", nullable = false)
    private LocalDateTime dataCreazione;

    public Utente(String nome, String cognome, String email, String password, RuoloUtente ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.dataCreazione = LocalDateTime.now();
    }
}
