package matteobenetazzo.testweek3bu2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import matteobenetazzo.testweek3bu2.entities.RuoloUtente;

public record UtentiDTO(
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 30, message = "Il nome deve essere tra 2 e 30 caratteri")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio")
        @Size(min = 2, max = 30, message = "Il cognome deve essere tra 2 e 30 caratteri")
        String cognome,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
        String password,

        RuoloUtente ruolo
) {
}
