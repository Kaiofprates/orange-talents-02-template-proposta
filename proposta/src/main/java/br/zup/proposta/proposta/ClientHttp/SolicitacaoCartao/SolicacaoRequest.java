package br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao;

public class SolicacaoRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public SolicacaoRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
