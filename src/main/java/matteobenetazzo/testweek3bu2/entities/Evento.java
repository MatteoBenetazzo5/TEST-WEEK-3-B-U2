package matteobenetazzo.testweek3bu2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Evento {

    @Id
    @GeneratedValue
    @Column(name = "id_evento")
    private UUID id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "data_evento", nullable = false)
    private LocalDateTime dataEvento;

    @Column(nullable = false)
    private String luogo;

    @Column(name = "posti_totali", nullable = false)
    private int postiTotali;

    @Column(name = "posti_disponibili", nullable = false)
    private int postiDisponibili;

    // creatore dell'evento (1 utente può creare tanti eventi)
    @ManyToOne
    @JoinColumn(name = "creatore_id", nullable = false)
    private Utente creatore;

    public Evento(String titolo, String descrizione, LocalDateTime dataEvento, String luogo, int postiTotali, Utente creatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataEvento = dataEvento;
        this.luogo = luogo;
        this.postiTotali = postiTotali;
        this.postiDisponibili = postiTotali;
        this.creatore = creatore;
    }
}
