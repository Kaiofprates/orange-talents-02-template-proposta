package br.zup.proposta.proposta.Bloqueio;

import br.zup.proposta.proposta.ClientHttp.Avisos.AvisoApiClient;
import br.zup.proposta.proposta.ClientHttp.Avisos.AvisoApiRequest;
import br.zup.proposta.proposta.ClientHttp.Avisos.AvisoResponse;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.ApiBloqueio;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioRequest;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioResponse;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaResponse;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicacaoRequest;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoClient;
import feign.FeignException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("dev")
public class ApiLegadaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApiBloqueio apiBloqueio;

    @Autowired
    private BuscaPropostaClient buscaPropostaClient;


    @Autowired
    private AvisoApiClient avisoApiClient;

    @Autowired
    private SolicitacaoClient client;

    private  final Logger logger  =  LoggerFactory.getLogger(ApiLegadaTest.class);


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
            Assertions.fail(response.getResultado().toString());

        }catch (FeignException e){
            // captura a mensagem de erro como resultado esperado de FALHA a api externa, por não existir o cartão
            logger.info(e.getMessage());

        }
    }

    @Test
    @DisplayName("Deveria testar o comportamento da notificação de avisos")
    public void avisoTest(){

        try{
            LocalDate data = LocalDate.now().plusDays(7);
            AvisoApiRequest request = new AvisoApiRequest("Irlanda",data.toString());
            AvisoResponse response = avisoApiClient.notificaAviso("9521-4187-6395-7650",request);

            // como o feign não retorna nada para status no range de 4** não espero receber o resultado da api

            Assertions.fail(response.getResultado().toString());
        }catch (FeignException e){
            logger.error(e.getMessage());
        }

    }


}
