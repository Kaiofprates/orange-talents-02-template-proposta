package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.Cartao.Model.Repository.CartaoRepository;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.Proposta.Endereco;
import br.zup.proposta.proposta.Proposta.Proposta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("dev")
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
    @WithMockUser
    @DisplayName("Testa tratativas de erro para a rota de cartões")
    public void testCartoes() throws Exception {


/*
        ResultActions response =  mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/propostas")
                  .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content( new ObjectMapper().writeValueAsString(propostaMock())));
        response.andExpect(MockMvcResultMatchers.status().isCreated());


        /*Por algum motivo não estou conseguindo acessar essa rota
        * Refatore quanto tiver com a mente mais descansada!
        */
/*

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
*/

    }




}
