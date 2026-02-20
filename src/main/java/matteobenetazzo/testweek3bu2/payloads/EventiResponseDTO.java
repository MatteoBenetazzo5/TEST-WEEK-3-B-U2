package matteobenetazzo.testweek3bu2.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventiResponseDTO(
        UUID id,
        String titolo,
        String descrizione,
        LocalDateTime dataEvento,
        String luogo,
        int postiTotali,
        int postiDisponibili,
        UUID creatoreId
) {
}
