package br.zup.proposta.proposta.Cartao.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Carteiras {

    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private LocalDateTime associadaEm = LocalDateTime.now();
    private String emissor;
    private String idcartao;

    @Deprecated
    public Carteiras(){}

    public Carteiras( String email, String emissor) {
        this.email = email;
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


}
