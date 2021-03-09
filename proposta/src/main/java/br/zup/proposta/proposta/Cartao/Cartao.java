package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicacaoRequest;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoClient;
import br.zup.proposta.proposta.Proposta.Estado;
import br.zup.proposta.proposta.Proposta.Proposta;
import br.zup.proposta.proposta.Proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cartao {

    @Autowired
    private SolicitacaoClient client;

    @Autowired
    private PropostaRepository repository;

    private final Logger logger  = LoggerFactory.getLogger(Cartao.class);

    /**
     * Primeira implementação, observar a possibilidade de refatorar no futuro!
     */

    public void avaliaCartao(Proposta proposta){
        SolicacaoRequest request = new SolicacaoRequest(proposta.getDocumento(),proposta.getNome(),proposta.getId());

        try{

            /**
             *  Essa primeira implementação está muito dependende da resposta de erro da api
             *  Kaio do futuro, tente fazer uma checagem mais consisa do que vem da api
             *  talvez usando o próprio tratamento de error do Feign!
             */

            String avaliacao = client.cadastra(request).getResultadoSolicitacao();
            proposta.setStatus(Estado.ELEGIVEL);
            repository.save(proposta);
            logger.info("Proposta avaliada com sucesso!");
            logger.info(avaliacao);

        }catch (FeignException e){
            logger.error(e.getMessage());
        }
    }

}
