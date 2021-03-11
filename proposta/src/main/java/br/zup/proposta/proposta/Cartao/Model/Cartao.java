package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
@Entity
public class Cartao {

    @Id
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @OneToMany
    private List<Bloqueios> bloqueios;
    @OneToMany
    private List<Avisos> avisos;
    @OneToMany
    private List<Carteiras> carteiras;
    @OneToMany
    private List<Parcela> parcelas;
    private BigDecimal limite;
    @OneToOne(cascade = CascadeType.MERGE)
    private Renegociacao renegociacao;
    @OneToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "idCartao")
    private List<Digital> digitais = new ArrayList<>();
    @NotNull
    private Long idProposta;

    @Deprecated
    public Cartao(){}

    public Cartao(String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, List<Bloqueios> bloqueios, List<Avisos> avisos, List<Carteiras> carteiras, List<Parcela> parcelas,
                  BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento, @NotNull Long idProposta) {
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


    public void setDigitais(Digital digital) {
        digitais.add(digital);
    }

    public List<Digital> getDigitais() {
        return digitais;
    }

    public String getId() {
        return id;
    }
}
