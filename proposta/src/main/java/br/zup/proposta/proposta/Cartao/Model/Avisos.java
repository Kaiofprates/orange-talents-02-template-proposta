package br.zup.proposta.proposta.Cartao.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Avisos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "O campo de data não pode estar vazio")
    private LocalDate validoAte;
    private String destino;
    private LocalDateTime criadoEm = LocalDateTime.now();
    @NotBlank(message = "Falha ao recuperar informações do cliente")
    private String ipCliente;
    @NotBlank(message = "Falha ao recuperar informações do cliente")
    private String userAgent;
    @NotBlank(message = "Falha ao recuperar informações do cartão")
    private String idCartao;


    @Deprecated
    public Avisos(){}

    public Avisos(@Future @NotNull(message = "O campo de data não pode estar vazio") LocalDate validoAte,
                  String destino,
                  @NotBlank(message = "Falha ao recuperar informações do cliente") String ipCliente,
                  @NotBlank(message = "Falha ao recuperar informações do cliente") String userAgent,
                  @NotBlank(message = "Falha ao recuperar informações do cartão") String idCartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.idCartao = idCartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIdCartao() {
        return idCartao;
    }
}
