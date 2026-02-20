package matteobenetazzo.testweek3bu2.payloads;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        LocalDateTime timestamp
) {
}
