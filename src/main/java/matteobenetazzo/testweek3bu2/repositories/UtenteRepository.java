package matteobenetazzo.testweek3bu2.repositories;

import matteobenetazzo.testweek3bu2.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    // serve per fare il login (cerco l'utente tramite email)
    Optional<Utente> findByEmail(String email);
}
