package matteobenetazzo.testweek3bu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweek3bu2.entities.Evento;
import matteobenetazzo.testweek3bu2.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    // creo/salvo un evento
    public Evento salvaEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // lista di tutti gli eventi
    public List<Evento> trovaTutti() {
        return eventoRepository.findAll();
    }

    // trovo evento per id
    public Evento trovaPerId(UUID id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
    }

    // trovo eventi creati da un certo organizzatore
    public List<Evento> trovaEventiCreatiDa(UUID idCreatore) {
        return eventoRepository.findByCreatoreId(idCreatore);
    }

    // elimino evento
    public void eliminaEvento(UUID id) {
        Evento evento = trovaPerId(id);
        eventoRepository.delete(evento);
    }
}
