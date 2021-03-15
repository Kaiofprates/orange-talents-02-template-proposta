package br.zup.proposta.proposta.ClientHttp.Bloqueio;

public class BloqueioRequest {
    private String sistemaResponsavel;

    @Deprecated
    public BloqueioRequest(){};

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
