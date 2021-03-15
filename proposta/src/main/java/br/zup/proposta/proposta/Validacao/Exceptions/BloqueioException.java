package br.zup.proposta.proposta.Validacao.Exceptions;

public class BloqueioException extends  Exception{

    public BloqueioException() {
    }
    public BloqueioException(String message) {
        super(message);
    }
    public BloqueioException(String message, Throwable cause) {
        super(message, cause);
    }
}
