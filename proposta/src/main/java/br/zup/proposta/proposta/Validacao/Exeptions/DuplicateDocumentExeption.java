package br.zup.proposta.proposta.Validacao.Exeptions;

public class DuplicateDocumentExeption extends Exception{
    public DuplicateDocumentExeption() {
    }

    public DuplicateDocumentExeption(String message) {
        super(message);
    }

    public DuplicateDocumentExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
