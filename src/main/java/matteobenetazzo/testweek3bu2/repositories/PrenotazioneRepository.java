package matteobenetazzo.testweek3bu2.repositories;

import matteobenetazzo.testweek3bu2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    // serve per controllare se un utente ha già prenotato un certo evento
    Optional<Prenotazione> findByUtenteIdAndEventoId(UUID utenteId, UUID eventoId);

    // serve per contare quante prenotazioni ha un evento
    long countByEventoId(UUID eventoId);
}
