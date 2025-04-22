package br.com.vidarica.exceptions;

public class BancoDaoException extends Exception {
    public BancoDaoException(String message) {
        super(message);
    }

    public BancoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BancoDaoException(Throwable cause) {
        super(cause);
    }
}
