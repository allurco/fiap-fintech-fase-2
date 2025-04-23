package br.com.vidarica.exceptions;

public class InvestimentoDaoException extends Exception {
    public InvestimentoDaoException(String message) {
        super(message);
    }

    public InvestimentoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvestimentoDaoException(Throwable cause) {
        super(cause);
    }
}
