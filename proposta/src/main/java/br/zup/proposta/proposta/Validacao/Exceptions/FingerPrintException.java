package br.zup.proposta.proposta.Validacao.Exceptions;

public class FingerPrintException extends Exception {
    public FingerPrintException() {
    }
    public FingerPrintException(String message) {
        super(message);
    }
    public FingerPrintException(String message, Throwable cause) {
        super(message, cause);
    }
}
