package matteobenetazzo.testweek3bu2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni", uniqueConstraints = @UniqueConstraint(columnNames = {"utenti_id", "eventi_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prenotazione {

    @Id
    @GeneratedValue
    @Column(name = "id_prenotazione")
    private UUID id;

    @Column(name = "data_prenotazione", nullable = false)
    private LocalDateTime dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "utenti_id", nullable = false)
    private Utente utente;
    
    @ManyToOne
    @JoinColumn(name = "eventi_id", nullable = false)
    private Evento evento;

    public Prenotazione(Utente utente, Evento evento) {
        this.utente = utente;
        this.evento = evento;
        this.dataPrenotazione = LocalDateTime.now();
    }
}
