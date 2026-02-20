package matteobenetazzo.testweek3bu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Utente;
import matteobenetazzo.testweek3bu2.payloads.UtentiDTO;
import matteobenetazzo.testweek3bu2.payloads.UtentiResponseDTO;
import matteobenetazzo.testweek3bu2.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
@RequiredArgsConstructor
public class UtentiController {

    private final UtenteService utenteService;

    // POST -> crea utente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UtentiResponseDTO create(@RequestBody @Valid UtentiDTO payload) {

        Utente nuovo = new Utente(
                payload.nome(),
                payload.cognome(),
                payload.email(),
                payload.password(),
                payload.ruolo()
        );

        Utente salvato = utenteService.salvaUtente(nuovo);

        return new UtentiResponseDTO(
                salvato.getId(),
                salvato.getNome(),
                salvato.getCognome(),
                salvato.getEmail(),
                salvato.getRuolo(),
                salvato.getDataCreazione()
        );
    }

    // GET
    @GetMapping("/{id}")
    public UtentiResponseDTO getById(@PathVariable UUID id) {
        Utente u = utenteService.trovaPerId(id);

        return new UtentiResponseDTO(
                u.getId(),
                u.getNome(),
                u.getCognome(),
                u.getEmail(),
                u.getRuolo(),
                u.getDataCreazione()
        );
    }
}
