package br.zup.proposta.proposta.Jobs;

import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaClient;
import br.zup.proposta.proposta.ClientHttp.BuscaProposta.BuscaPropostaResponse;
import br.zup.proposta.proposta.Proposta.Estado;
import br.zup.proposta.proposta.Proposta.Proposta;
import br.zup.proposta.proposta.Proposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobDeAvaliacaoCartao {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private BuscaPropostaClient client;

    private  final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);

    @Scheduled(fixedDelayString= "${periodicidade.avalia-cartao}")
    private void avaliaCartoes() {
        List<Proposta> propostas = repository.findByStatusAndCartao(Estado.ELEGIVEL,null);
        if(!propostas.isEmpty()){
            propostas.stream().forEach(this::getCartaoId);
        }
   }
    public void getCartaoId(Proposta proposta){
        BuscaPropostaResponse response = client.busca(proposta.getId());
        proposta.setCartao(response.getId());
        repository.save(proposta);
        logger.info("Atualização de cadastro para o id "+ proposta.getId() + " gerada com sucesso");
    }


}
