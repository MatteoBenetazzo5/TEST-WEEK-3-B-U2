package matteobenetazzo.testweek3bu2.config;

import matteobenetazzo.testweek3bu2.exceptions.BadRequestException;
import matteobenetazzo.testweek3bu2.exceptions.NotFoundException;
import matteobenetazzo.testweek3bu2.payloads.ErrorsDTO;
import matteobenetazzo.testweek3bu2.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    // 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorsDTO> handleNotFound(NotFoundException ex) {
        // creo il body dell'errore con messaggio + data
        ErrorsDTO body = new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // 400 custom (BadRequestException)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorsDTO> handleBadRequest(BadRequestException ex) {
        ErrorsDTO body = new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // 400 validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // questa eccezione viene lanciata automaticamente quando @Valid fallisce
    public ResponseEntity<ErrorsWithListDTO> handleValidation(MethodArgumentNotValidException ex) {

        // qui prendo tutti gli errori di validazione e li trasformo in una lista di messaggi
        List<String> errorsList = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) // prendo solo il messaggio leggibile
                .collect(Collectors.toList());

        // in questo caso uso un DTO diverso perché devo restituire una LISTA di errori
        ErrorsWithListDTO body = new ErrorsWithListDTO(errorsList, LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // 500 generico
    @ExceptionHandler(Exception.class)
    // cattura qualsiasi altra eccezione non gestita sopra
    public ResponseEntity<ErrorsDTO> handleGeneric(Exception ex) {
        ErrorsDTO body = new ErrorsDTO("Errore generico del server", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}