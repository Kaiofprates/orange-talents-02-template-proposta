package br.zup.proposta.proposta.Cartao;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Parcela {

    @Id
    private String id;
    private int quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCracao;

    @Deprecated
    public Parcela(){}

    public Parcela(String id, int quantidade, BigDecimal valor, LocalDateTime dataDeCracao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCracao = dataDeCracao;
    }

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCracao() {
        return dataDeCracao;
    }
}
