package br.zup.proposta.proposta;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.Cartao.Model.CartaoRepository;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import br.zup.proposta.proposta.Proposta.Endereco;
import br.zup.proposta.proposta.Proposta.Proposta;
import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoTestes {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private BuscaPropostaClient client;


    public Proposta propostaMock(){
        Endereco endereco = new Endereco("Rua A ","123-A","Bairro B", "Cidade C","Estado D","321321");
        return  new Proposta("956.491.420-50","john@email.com","John Constantine",endereco, BigDecimal.TEN);
    }

    @Test
    @DisplayName("Testa tratativas de erro para a rota de cartões")
    public void testCartoes() throws Exception {

        ResultActions response =  mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/propostas")
                  .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content( new ObjectMapper().writeValueAsString(propostaMock())));
        response.andExpect(MockMvcResultMatchers.status().isCreated());


        /*Por algum motivo não estou conseguindo acessar essa rota
        * Refatore quanto tiver com a mente mais descansada!
        */


        ResultActions cartaoRequest =  mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9999/api/solicitacao/cartoes?idProposta=1"));


        List<Cartao> cartao = repository.findAll();
        String numeroCartao = cartao.get(0).getId();

        String fingerprint = "{\n" +
                "\t\"fingerprint\" : \"dm91IG5hbW9yYXIgYXJpZWxl\"\n" +
                "}";

        ResultActions responseCartao =  mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/cartoes/"+numeroCartao)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(fingerprint));

        responseCartao.andExpect(MockMvcResultMatchers.status().isCreated());

        String location = responseCartao.andReturn().getResponse().getHeader("Location");
        Assert.notNull(location);


    }




}
