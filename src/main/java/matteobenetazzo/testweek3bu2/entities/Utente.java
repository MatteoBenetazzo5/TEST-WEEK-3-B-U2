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

    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoloUtente ruolo;

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
