package br.zup.proposta.proposta.ClientHttp.Bloqueio;

public class BloqueioResponse {
    private BloqueioStatus resultado;

    @Deprecated
    public BloqueioResponse(){};

    public BloqueioResponse(BloqueioStatus resultado) {
        this.resultado = resultado;
    }

    public BloqueioStatus getResultado() {
        return resultado;
    }
}
