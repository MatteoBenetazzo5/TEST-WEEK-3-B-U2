package matteobenetazzo.testweek3bu2.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime timestamp
) {
}
