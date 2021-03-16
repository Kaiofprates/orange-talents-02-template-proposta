package br.zup.proposta.proposta.Cartao.Avisos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank(message = "O campo destino não pode ser vazio ou nulo")
    private String destino;
    @Future
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "O campo de data não pode estar vazio")
    private LocalDate validoAte;

    @Deprecated
    public AvisoRequest(){};

    public AvisoRequest(String destino,
                        LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
