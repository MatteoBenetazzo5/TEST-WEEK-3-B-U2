package matteobenetazzo.testweek3bu2.repositories;

import matteobenetazzo.testweek3bu2.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
    // serve per vedere tutti gli eventi creati da un certo organizzatore
    List<Evento> findByCreatoreId(UUID idCreatore);
}
