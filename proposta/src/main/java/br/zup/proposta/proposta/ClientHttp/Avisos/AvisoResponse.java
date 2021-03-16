package br.zup.proposta.proposta.ClientHttp.Avisos;

public class AvisoResponse {
    private AvisoStatus resultado;

    @Deprecated
    public AvisoResponse(){}

    public AvisoResponse(AvisoStatus resultado) {
        this.resultado = resultado;
    }

    public void setResultado(AvisoStatus resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "AvisoResponse{" +
                "resultado=" + resultado +
                '}';
    }

    public AvisoStatus getResultado() {
        return resultado;
    }
}
