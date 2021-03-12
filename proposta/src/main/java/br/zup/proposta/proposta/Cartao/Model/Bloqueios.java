package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Bloqueios {
    @Id
    private String id;
    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();

    private String sistemaResponsavel;

    private String  ipCliente;

    private Boolean ativo;

    @Deprecated
    public Bloqueios(){}

    public Bloqueios(String id, String sistemaResponsavel, String ipCliente, Boolean ativo) {
        this.id = id;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ipCliente = ipCliente;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
