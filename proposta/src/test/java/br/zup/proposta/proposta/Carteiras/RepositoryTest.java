package br.zup.proposta.proposta.Carteiras;

import br.zup.proposta.proposta.Bloqueio.ApiLegadaTest;
import br.zup.proposta.proposta.Cartao.Carteiras.CarteirasDisponiveis;
import br.zup.proposta.proposta.Cartao.Model.Carteiras;
import br.zup.proposta.proposta.Cartao.Model.CarteirasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("dev")
public class RepositoryTest {

    @Autowired
    private CarteirasRepository repository;

    private  final Logger logger  =  LoggerFactory.getLogger(ApiLegadaTest.class);

    @Test
    @DisplayName("Testa metodo de busca por id do cartão e do emissor")
    public void testaFindByIdCartaoAndEmissor(){
        String idCartao = "6381-9947-9319-7973";
        String emissor = CarteirasDisponiveis.PAYPAL.toString();
        Carteiras carteiras = new Carteiras("email@Teste.com",emissor);
        carteiras.setIdCartao(idCartao);
        repository.save(carteiras);
        Optional<Carteiras> carteiraBuscada = repository.findByIdcartaoAndEmissor(idCartao,emissor);
        logger.info(carteiraBuscada.toString());
        Assert.notNull(carteiraBuscada.isPresent());
    }

    @Test
    @DisplayName("Testa falha do metodo de busca por id do cartão e do emissor")
    public void testaFalhaDoFindByIdCartaoAndEmissor(){
        String idCartao = "6381-9947-9319-7971";
        String emissor = CarteirasDisponiveis.PAYPAL.toString();
        Carteiras carteiras = new Carteiras("mail@Teste.com",emissor);
        carteiras.setIdCartao(idCartao);
        repository.save(carteiras);
        Optional<Carteiras> carteiraBuscada = repository.findByIdcartaoAndEmissor(idCartao,CarteirasDisponiveis.SANSUNG_PAY.toString());
        logger.info(carteiraBuscada.toString());
        Assert.notNull(!carteiraBuscada.isPresent());

    }



}
