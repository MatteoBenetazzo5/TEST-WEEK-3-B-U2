package matteobenetazzo.testweek3bu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Prenotazione;
import matteobenetazzo.testweek3bu2.payloads.PrenotazioniDTO;
import matteobenetazzo.testweek3bu2.payloads.PrenotazioniResponseDTO;
import matteobenetazzo.testweek3bu2.services.PrenotazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioniController {

    private final PrenotazioneService prenotazioneService;

    // POST -> crea prenotazione
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioniResponseDTO create(@RequestBody @Valid PrenotazioniDTO payload) {

        Prenotazione p = prenotazioneService.creaPrenotazione(payload.utenteId(), payload.eventoId());

        return new PrenotazioniResponseDTO(
                p.getId(),
                p.getDataPrenotazione(),
                p.getUtente().getId(),
                p.getEvento().getId()
        );
    }
}
