package br.zup.proposta.proposta.Carteiras;

import br.zup.proposta.proposta.Cartao.Carteiras.CarteirasDisponiveis;
import br.zup.proposta.proposta.Cartao.Carteiras.CarteirasRequest;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraClient;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraResponse;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;


@SpringBootTest
@AutoConfigureMockMvc
@Profile("dev")
public class CarteirasTeste {


    @Autowired
    private CarteiraClient carteiraClient;

    private final Logger logger = (Logger) LoggerFactory.getLogger(CarteirasTeste.class);

    @Test
    @DisplayName("Testa comportamento do Feign com endpoint de carteiras")
    public  void testaNovaCarteira(){

        try{

            // é  possível registrar a mesma carteira com um email diferente

            CarteirasRequest request = new CarteirasRequest("email@teste1.com.br", CarteirasDisponiveis.PAYPAL);
            CarteiraResponse response = carteiraClient.adicionaCarteira("6381-9947-9319-7973",request);
            Assertions.fail(response.getResultado());

        }catch (FeignException e){
            // resultado deve ser FALHA e o id null
            logger.error(e.getMessage());
        }


    }



}
