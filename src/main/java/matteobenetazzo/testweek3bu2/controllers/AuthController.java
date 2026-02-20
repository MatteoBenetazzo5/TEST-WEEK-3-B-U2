package matteobenetazzo.testweek3bu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Utente;
import matteobenetazzo.testweek3bu2.exceptions.BadRequestException;
import matteobenetazzo.testweek3bu2.payloads.LoginDTO;
import matteobenetazzo.testweek3bu2.payloads.LoginResponseDTO;
import matteobenetazzo.testweek3bu2.payloads.UtentiDTO;
import matteobenetazzo.testweek3bu2.payloads.UtentiResponseDTO;
import matteobenetazzo.testweek3bu2.security.JWTTools;
import matteobenetazzo.testweek3bu2.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UtenteService utenteService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    // REGISTER
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtentiResponseDTO register(@RequestBody @Valid UtentiDTO payload) {

        // cripto la password prima di salvarla nel database
        String passwordCriptata = passwordEncoder.encode(payload.password());

        // creo l'entità Utente usando i dati del DTO
        Utente nuovo = new Utente(
                payload.nome(),
                payload.cognome(),
                payload.email(),
                passwordCriptata,
                payload.ruolo()
        );

        Utente salvato = utenteService.salvaUtente(nuovo);

        // restituisco i dati senza includere la password
        return new UtentiResponseDTO(
                salvato.getId(),
                salvato.getNome(),
                salvato.getCognome(),
                salvato.getEmail(),
                salvato.getRuolo(),
                salvato.getDataCreazione()
        );
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginDTO payload) {

        // recupero l'utente tramite email
        Utente u = utenteService.trovaPerEmail(payload.email());

        // confronto la password inserita con quella criptata nel DB
        if (!passwordEncoder.matches(payload.password(), u.getPassword())) {
            throw new BadRequestException("Credenziali non valide");
        }

        // creo il token JWT usando l'id dell'utente
        String token = jwtTools.createToken(u.getId());
        return new LoginResponseDTO(token);
    }
}
