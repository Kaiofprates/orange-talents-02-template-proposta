package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.Validacao.Exceptions.FingerPrintException;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String fingerprint;

    @Deprecated
    public BiometriaRequest(){}

    public BiometriaRequest(@NotBlank String fingerprint) {
        this.fingerprint = fingerprint;
    }
    public String getFingerprint() {
        return fingerprint;
    }


    public Boolean isValid() throws FingerPrintException {
        try{
            byte[] decode = Base64.getDecoder().decode(fingerprint.getBytes());
            return true;
        }catch (Exception e){
            throw new FingerPrintException("Base64 encode inválido");
        }
    }

}
