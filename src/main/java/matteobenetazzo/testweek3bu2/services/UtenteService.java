package matteobenetazzo.testweek3bu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Utente;
import matteobenetazzo.testweek3bu2.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;

    // salvo un nuovo utente
    public Utente salvaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    // trovo utente per id
    public Utente trovaPerId(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}