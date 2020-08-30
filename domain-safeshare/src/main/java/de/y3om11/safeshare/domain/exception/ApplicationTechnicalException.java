package de.y3om11.safeshare.domain.exception;

public class ApplicationTechnicalException extends RuntimeException {
    public ApplicationTechnicalException(String msg){
        super(msg);
    }
}
