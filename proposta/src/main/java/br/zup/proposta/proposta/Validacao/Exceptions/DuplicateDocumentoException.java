package br.zup.proposta.proposta.Validacao.Exceptions;

public class DuplicateDocumentoException extends Exception{
    public DuplicateDocumentoException() {
    }
    public DuplicateDocumentoException(String message) {
        super(message);
    }
    public DuplicateDocumentoException(String message, Throwable cause) {
        super(message, cause);
    }
}
