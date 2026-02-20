package matteobenetazzo.testweek3bu2.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioniDTO(
        @NotNull(message = "utenteId obbligatorio")
        UUID utenteId,

        @NotNull(message = "eventoId obbligatorio")
        UUID eventoId
) {
}
