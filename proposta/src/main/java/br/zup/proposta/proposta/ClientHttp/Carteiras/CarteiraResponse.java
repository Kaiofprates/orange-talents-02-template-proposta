package br.zup.proposta.proposta.ClientHttp.Carteiras;

public class CarteiraResponse {
    private String resultado;
    private String id;

    @Deprecated
    public CarteiraResponse(){};

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
