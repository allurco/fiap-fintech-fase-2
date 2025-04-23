package br.com.vidarica.exceptions;

public class AporteDaoException extends Exception {
    public AporteDaoException(String message) {
        super(message);
    }

    public AporteDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AporteDaoException(Throwable cause) {
        super(cause);
    }
}
