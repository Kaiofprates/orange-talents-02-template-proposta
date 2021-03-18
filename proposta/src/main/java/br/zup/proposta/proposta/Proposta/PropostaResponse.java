package br.zup.proposta.proposta.Proposta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Locale;

public class PropostaResponse {

    @NotNull
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String documento;
    @NotNull
    private Estado status;

    public PropostaResponse( @Valid  Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.documento = ofucaDocumento(proposta.getDocumento());
        this.status = proposta.getStatus();
    }

    /*
    * Preferimos retornar o documento ofuscado
    */

    private String ofucaDocumento(String documento){
        String retorno = documento.trim().toLowerCase(Locale.ROOT).split("//")[0].replaceAll("\\D","");

        if(retorno.length() == 11){
            return  "***.***.***-" + retorno.substring(9,11);
        }else {
            return "**.***.***/****-" + retorno.substring(12,14);
        }

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Estado getStatus() {
        return status;
    }
}
