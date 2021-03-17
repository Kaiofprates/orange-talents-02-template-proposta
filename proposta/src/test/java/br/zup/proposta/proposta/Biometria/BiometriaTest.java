package br.zup.proposta.proposta.Biometria;

import br.zup.proposta.proposta.Bloqueio.ApiLegadaTest;
import br.zup.proposta.proposta.Cartao.Biometria.BiometriaRequest;
import br.zup.proposta.proposta.Cartao.Biometria.CartaoController;
import br.zup.proposta.proposta.Validacao.Exceptions.FingerPrintException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@SpringBootTest
@Profile("dev")
public class BiometriaTest {

    @Mock
    private UriComponentsBuilder builder;

    @Mock
    private BiometriaRequest biometriaRequest;

    private  final Logger logger  =  LoggerFactory.getLogger(ApiLegadaTest.class);


    @Test
    @DisplayName("Teste do controller de cartão para o status code 404")
    void cartaoTeste() throws FingerPrintException {

        /*
        * Apenas um exercício de como se usaria o mockito
        * para testar esse controller.
        */

        CartaoController cartaoController = mock(CartaoController.class);
        when(cartaoController.cadastraFingerprint(anyString(),eq(biometriaRequest),eq(builder)))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        Assertions.assertEquals(cartaoController.cadastraFingerprint("1231231",biometriaRequest,builder),
                new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @Test
    @DisplayName("Deve passar o metodo de validação de Base64")
    void validaBiometria() throws FingerPrintException {
        BiometriaRequest request = new BiometriaRequest("bWFyaWEgdGVyZXphIHRlIGFtbw==");
        Assertions.assertTrue(request.isValid());
    }

    @Test
    @DisplayName("Deve estourar uma exception no metodo de validação de Base64")
    void  biometriaInvalida() throws  FingerPrintException {

        BiometriaRequest request = new BiometriaRequest("bWFyaWEgdGVyZXphIHRlIGFtbw=1");
        try{
            request.isValid();
            Assertions.fail();
        }catch (FingerPrintException e){
            logger.info(e.getMessage());
        }
    }


}
