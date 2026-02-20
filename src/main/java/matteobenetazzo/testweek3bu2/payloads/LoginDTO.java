package matteobenetazzo.testweek3bu2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Email obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotBlank(message = "Password obbligatoria")
        String password
) {
}
