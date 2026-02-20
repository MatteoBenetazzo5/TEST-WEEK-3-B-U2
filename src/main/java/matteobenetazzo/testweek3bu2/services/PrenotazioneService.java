package matteobenetazzo.testweek3bu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Evento;
import matteobenetazzo.testweek3bu2.entities.Prenotazione;
import matteobenetazzo.testweek3bu2.entities.Utente;
import matteobenetazzo.testweek3bu2.exceptions.BadRequestException;
import matteobenetazzo.testweek3bu2.exceptions.NotFoundException;
import matteobenetazzo.testweek3bu2.repositories.EventoRepository;
import matteobenetazzo.testweek3bu2.repositories.PrenotazioneRepository;
import matteobenetazzo.testweek3bu2.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final EventoRepository eventoRepository;

    public Prenotazione creaPrenotazione(UUID idUtente, UUID idEvento) {

        // 1) prendo utente e evento
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        // 2) controllo doppia prenotazione
        prenotazioneRepository.findByUtenteIdAndEventoId(idUtente, idEvento)
                .ifPresent(p -> {
                    throw new BadRequestException("Hai già prenotato questo evento");
                });

        // 3) controllo posti disponibili
        if (evento.getPostiDisponibili() <= 0) {
            throw new BadRequestException("Posti esauriti per questo evento");
        }

        // 4) creo prenotazione
        Prenotazione nuovaPrenotazione = new Prenotazione(utente, evento);

        // 5) aggiorno i posti disponibili dell'evento
        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);

        // 6) salvo prenotazione
        return prenotazioneRepository.save(nuovaPrenotazione);
    }
}
