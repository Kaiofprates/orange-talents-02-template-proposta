package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Avisos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime validoAte;
    private String destino;

    @Deprecated
    public Avisos(){}

    public Avisos(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
