package matteobenetazzo.testweek3bu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Evento;
import matteobenetazzo.testweek3bu2.entities.Utente;
import matteobenetazzo.testweek3bu2.payloads.EventiDTO;
import matteobenetazzo.testweek3bu2.payloads.EventiResponseDTO;
import matteobenetazzo.testweek3bu2.services.EventoService;
import matteobenetazzo.testweek3bu2.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
@RequiredArgsConstructor
public class EventiController {

    private final EventoService eventoService;
    private final UtenteService utenteService;

    // GET -> lista tutti eventi
    @GetMapping
    public List<EventiResponseDTO> getAll() {

        // recupero tutti gli eventi e li trasformo in DTO
        return eventoService.trovaTutti()
                .stream()
                .map(e -> new EventiResponseDTO(
                        e.getId(),
                        e.getTitolo(),
                        e.getDescrizione(),
                        e.getDataEvento(),
                        e.getLuogo(),
                        e.getPostiTotali(),
                        e.getPostiDisponibili(),
                        e.getCreatore().getId()
                ))
                .toList();
    }

    // GET -> dettaglio evento
    @GetMapping("/{id}")
    public EventiResponseDTO getById(@PathVariable UUID id) {

        // recupero l'evento tramite id
        Evento e = eventoService.trovaPerId(id);

        // restituisco il DTO con i dati dell'evento
        return new EventiResponseDTO(
                e.getId(),
                e.getTitolo(),
                e.getDescrizione(),
                e.getDataEvento(),
                e.getLuogo(),
                e.getPostiTotali(),
                e.getPostiDisponibili(),
                e.getCreatore().getId()
        );
    }

    // POST -> crea evento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventiResponseDTO create(@RequestBody @Valid EventiDTO payload) {

        // recupero il creatore dell'evento dal suo id
        Utente creatore = utenteService.trovaPerId(payload.creatoreId());

        // creo il nuovo evento usando i dati del payload
        Evento nuovo = new Evento(
                payload.titolo(),
                payload.descrizione(),
                payload.dataEvento(),
                payload.luogo(),
                payload.postiTotali(),
                creatore
        );

        Evento salvato = eventoService.salvaEvento(nuovo);

        // restituisco il DTO dell'evento salvato
        return new EventiResponseDTO(
                salvato.getId(),
                salvato.getTitolo(),
                salvato.getDescrizione(),
                salvato.getDataEvento(),
                salvato.getLuogo(),
                salvato.getPostiTotali(),
                salvato.getPostiDisponibili(),
                salvato.getCreatore().getId()
        );
    }

    // DELETE -> elimina evento
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        // elimino l'evento tramite id
        eventoService.eliminaEvento(id);
    }
}
