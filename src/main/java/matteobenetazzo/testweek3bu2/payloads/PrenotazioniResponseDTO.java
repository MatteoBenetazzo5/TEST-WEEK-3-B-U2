package matteobenetazzo.testweek3bu2.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrenotazioniResponseDTO(
        UUID id,
        LocalDateTime dataPrenotazione,
        UUID utenteId,
        UUID eventoId
) {
}