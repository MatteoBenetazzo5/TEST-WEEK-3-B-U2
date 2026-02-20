package matteobenetazzo.testweek3bu2.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String messaggio) {
        super(messaggio);
    }
}
