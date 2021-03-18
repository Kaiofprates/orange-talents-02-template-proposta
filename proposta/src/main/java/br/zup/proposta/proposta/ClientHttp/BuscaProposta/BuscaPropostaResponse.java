package br.zup.proposta.proposta.ClientHttp.BuscaProposta;

import br.zup.proposta.proposta.Cartao.Model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BuscaPropostaResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueios> bloqueios;
    private List<Avisos> avisos;
    private List<Carteiras> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private Long idProposta;


    @Deprecated
    public BuscaPropostaResponse(){}

    public BuscaPropostaResponse(String id, LocalDateTime emitidoEm, String titular, List<Bloqueios> bloqueios, List<Avisos> avisos, List<Carteiras> carteiras,
                                 List<Parcela> parcelas, BigDecimal limite,
                                 Renegociacao renegociacao, Vencimento vencimento, Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueios> getBloqueios() {
        return bloqueios;
    }

    public List<Avisos> getAvisos() {
        return avisos;
    }

    public List<Carteiras> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Cartao toModel() {
        return new Cartao(id,emitidoEm,titular,bloqueios,avisos,carteiras,parcelas,limite,renegociacao,vencimento,idProposta);
    }
}
