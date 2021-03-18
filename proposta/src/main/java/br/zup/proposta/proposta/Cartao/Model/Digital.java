package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "digitais")
public class Digital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime criadoEm = LocalDateTime.now();
    @NotBlank
    private String idCartao;
    @NotBlank
    private String fingerprint;

    @Deprecated
    public Digital(){}

    public Digital(@NotBlank String idCartao, @NotBlank String fingerprint) {
        this.idCartao = idCartao;
        this.fingerprint = fingerprint;
    }

    @Override
    public String toString() {
        return "Digital{" +
                "id=" + id +
                ", criadoEm=" + criadoEm +
                ", idCartao='" + idCartao + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
}
