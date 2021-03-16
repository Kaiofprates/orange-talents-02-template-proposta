package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicacaoRequest;
import br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao.SolicitacaoClient;
import br.zup.proposta.proposta.Metricas.MinhasMetricas;
import br.zup.proposta.proposta.Proposta.Estado;
import br.zup.proposta.proposta.Proposta.Proposta;
import br.zup.proposta.proposta.Proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliaCartao {

    @Autowired
    private SolicitacaoClient client;

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private MinhasMetricas minhasMetricas;


    private final Logger logger  = LoggerFactory.getLogger(AvaliaCartao.class);


    public void avaliaCartao(Proposta proposta){
        SolicacaoRequest request = new SolicacaoRequest(proposta.getDocumento(),proposta.getNome(),proposta.getId());

        try{
            String avaliacao = client.cadastra(request).getResultadoSolicitacao();
            proposta.setStatus(Estado.ELEGIVEL);
            repository.save(proposta);
            minhasMetricas.contaPropostas();

            logger.info("Proposta avaliada com sucesso!");
            logger.info(avaliacao);

        }catch (FeignException e){
            logger.error(e.getMessage());
        }
    }

}
