package matteobenetazzo.testweek3bu2.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(
        List<String> errors,
        LocalDateTime timestamp
) {
}