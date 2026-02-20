package matteobenetazzo.testweek3bu2.payloads;

import matteobenetazzo.testweek3bu2.entities.RuoloUtente;

import java.time.LocalDateTime;
import java.util.UUID;

public record UtentiResponseDTO(
        UUID id,
        String nome,
        String cognome,
        String email,
        RuoloUtente ruolo,
        LocalDateTime dataCreazione
) {
}