package br.zup.proposta.proposta.FeignTestes;

import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicacaoRequest;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoClient;
import feign.FeignException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("dev")
public class ClientHttpTeste {

    @Autowired
    private BuscaPropostaClient buscaPropostaClient;

    @Autowired
    private SolicitacaoClient solicitacaoClient;


    private enum Status{
        SEM_RESTRICAO,
        COM_RESTRICAO
    }

    @Test
    @DisplayName("Testa o comportamento do Feign com a api de propostas e cartao")
    public void testaClientProposta(){
        SolicacaoRequest request = new SolicacaoRequest("829.136.110-01","John Doe",1L);
        String result = solicitacaoClient.cadastra(request).getResultadoSolicitacao();
        Assertions.assertNotNull(result);
        Assert.assertEquals(result,Status.SEM_RESTRICAO.toString());

        String cartao =  buscaPropostaClient.busca(1L).getId();
        Assertions.assertNotNull(cartao);

    }


    @Test
    @DisplayName("Testa o comportamento da api de Cartao ")
    public void testaClientPropostaComRestriCao(){
        try{
            SolicacaoRequest request = new SolicacaoRequest("318.742.570-43","John Doe",12L);
        }catch (FeignException e){
            Assertions.fail();
        }

    }


}
