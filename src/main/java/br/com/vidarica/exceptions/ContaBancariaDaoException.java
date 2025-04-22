package br.com.vidarica.exceptions;

public class ContaBancariaDaoException extends Exception {
    public ContaBancariaDaoException(String message) {
        super(message);
    }

    public ContaBancariaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaBancariaDaoException(Throwable cause) {
        super(cause);
    }
}
