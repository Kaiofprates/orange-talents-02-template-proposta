package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class Carteiras {

    @Id
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;
    private String idcartao;

    @Deprecated
    public Carteiras(){}

    public Carteiras(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }
    public String getEmissor() {
        return emissor;
    }

    public void setIdCartao(String idCartao) {
        this.idcartao = idCartao;
    }


    @Override
    public String toString() {
        return "Carteiras{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", associadaEm=" + associadaEm +
                ", emissor='" + emissor + '\'' +
                ", idCartao='" + idcartao + '\'' +
                '}';
    }


}
