package matteobenetazzo.testweek3bu2.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventiDTO(
        @NotBlank(message = "Il titolo è obbligatorio")
        String titolo,

        @NotBlank(message = "La descrizione è obbligatoria")
        String descrizione,

        @NotNull(message = "La data evento è obbligatoria")
        LocalDateTime dataEvento,

        @NotBlank(message = "Il luogo è obbligatorio")
        String luogo,

        @Min(value = 1, message = "I posti totali devono essere almeno 1")
        int postiTotali,

        @NotNull(message = "Il creatoreId è obbligatorio")
        UUID creatoreId
) {
}
