package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.Cartao.Model.CartaoRepository;
import br.zup.proposta.proposta.Cartao.Model.Digital;
import br.zup.proposta.proposta.Validacao.Exceptions.FingerPrintException;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

    /*
    * Verifica se o fingerprint está no encode correto
    * como em caso de falha se lanca uma exceção
    * precisei tratá-la com uma exception específica
    */

    public Boolean isValid() throws FingerPrintException {
        try{
            byte[] decode = Base64.getDecoder().decode(fingerprint.getBytes());
            return true;
        }catch (Exception e){
            throw new FingerPrintException("Base64 encode inválido");
        }
    }

    public Long salvaDigital(CartaoRepository cartaoRepository, String id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        Assert.isTrue(cartao.isPresent(),"Falha ao recuperar informações do cartão de crédito");

        if(cartao.isPresent()){

            /* refatorar essa lógia de atualização e recebimento do id
            *  Talvez aqui um entitymanager fosse uma melhor opção.
            */

            Digital digital = new Digital(id,fingerprint);
            Cartao update = cartao.get();

            update.setDigitais(digital);

            List<Digital> digitais = update.getDigitais();
            cartaoRepository.save(update);

            return update.getDigitais().get(digitais.size() - 1).getId();
        }
        return null;
    }
}
