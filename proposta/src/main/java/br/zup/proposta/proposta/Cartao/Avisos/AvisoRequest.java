package br.zup.proposta.proposta.Cartao.Avisos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AvisoRequest {

    @NotBlank(message = "O campo destino não pode ser vazio ou nulo")
    private String destino;
    @Future
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "O campo de data não pode estar vazio")
    private LocalDate validoAte;

    private LocalDateTime criadoEm = LocalDateTime.now();

    private String ipCliente;
    private String userAgent;

    @Deprecated
    public AvisoRequest(){};

    public AvisoRequest(String destino,
                        LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    @Override
    public String toString() {
        return "AvisoRequest{" +
                "destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                ", criadoEm=" + criadoEm +
                ", ipCliente='" + ipCliente + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
