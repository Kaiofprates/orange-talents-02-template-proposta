package br.zup.proposta.proposta.Jobs;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.Cartao.Model.Repository.CartaoRepository;
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
    private CartaoRepository cartaoRepository;

    @Autowired
    private BuscaPropostaClient client;

    private  final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);

    @Scheduled(fixedDelayString= "${periodicidade.avalia-cartao}")
    private void avaliaCartoes() {
        try{
            List<Proposta> propostas = repository.findByStatusAndCartao(Estado.ELEGIVEL,null);
            if(!propostas.isEmpty()){
                propostas.stream().forEach(this::SaveCartaoId);
            }
        }catch (Exception e){
            logger.info("----------- Aguardando base de dados ------------ ");
        }

   }
    public void SaveCartaoId(Proposta proposta){
        try{
            BuscaPropostaResponse response = client.busca(proposta.getId());
            Cartao cartao = response.toModel();

            if(cartao != null) {
                cartaoRepository.save(cartao);
            }
            proposta.setCartao(response.getId());
            repository.save(proposta);
            logger.info("Atualização de cadastro para o id "+ proposta.getId() + " gerada com sucesso");
        }catch (Exception e){
            logger.info("Aguardando base de dados ");
        }

    }


}
