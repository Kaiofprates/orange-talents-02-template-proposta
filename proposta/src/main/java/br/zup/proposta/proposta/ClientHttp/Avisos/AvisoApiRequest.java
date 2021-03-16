package br.zup.proposta.proposta.ClientHttp.Avisos;

public class AvisoApiRequest {

    private String destino;
    private String validoAte;

    public AvisoApiRequest(String destino, String validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public String getValidoAte() {
        return validoAte;
    }
}
