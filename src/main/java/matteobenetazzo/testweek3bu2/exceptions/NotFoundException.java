package matteobenetazzo.testweek3bu2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messaggio) {
        super(messaggio);
    }
}
