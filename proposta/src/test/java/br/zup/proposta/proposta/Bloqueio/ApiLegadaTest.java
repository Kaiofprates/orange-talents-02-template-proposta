package br.zup.proposta.proposta.Bloqueio;

import br.zup.proposta.proposta.ClientHttp.Bloqueio.ApiBloqueio;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioRequest;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioResponse;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaResponse;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicacaoRequest;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoClient;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoResponse;
import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import feign.FeignException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiLegadaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApiBloqueio apiBloqueio;

    @Autowired
    private BuscaPropostaClient buscaPropostaClient;

    @Autowired
    private SolicitacaoClient client;

    private  final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);


    /*
    * A api legada retorna 400 para qualquer erro, mas não informa nada no corpo
    *
    */

    @Test
    @DisplayName("Deveria testar o comportamento da api de bloqueio")
    public void bloqueioTest(){
        try {
            SolicacaoRequest request = new SolicacaoRequest("277.375.130-27","John Doe", 1L);
            client.cadastra(request);
            BuscaPropostaResponse buscaPropostaResponse = buscaPropostaClient.busca(1L);
            String idCartao = buscaPropostaResponse.getId();
            BloqueioRequest bloqueioRequest = new BloqueioRequest("api_propostas");
            BloqueioResponse response = apiBloqueio.notificaBloqueio(idCartao,bloqueioRequest);
            logger.info(response.getResultado().toString());
        }catch (FeignException e){
            logger.error(e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }


}
