package br.zup.proposta.proposta.Cartao.Bloqueio;

import br.zup.proposta.proposta.Cartao.Model.Bloqueios;
import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.ApiBloqueio;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioRequest;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioStatus;
import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import br.zup.proposta.proposta.Validacao.Exceptions.BloqueioException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AvaliaBloqueio {

    private String id;
    private String ip;
    private String userAgent;
    private Cartao cartao;

    @Deprecated
    public AvaliaBloqueio(){};

    public AvaliaBloqueio(String id, String ip, String userAgent, Cartao cartao) {
        this.id = id;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    @Transactional
    public ResponseEntity<?> autorizaBloqueio(EntityManager manager, ApiBloqueio apiBloqueio) throws BloqueioException {

        Bloqueios bloqueios = manager.find(Bloqueios.class,id);

        if(bloqueios == null){

            try{
                // -- tentar bloqueio do cartão na api legada
                BloqueioRequest request = new BloqueioRequest(userAgent);
                apiBloqueio.notificaBloqueio(id,request);
                cartao.setStatusBloqueio(BloqueioStatus.BLOQUEADO);

                manager.persist(processaBloqueio(ip,userAgent,id));
                return ResponseEntity.ok().build();

            }catch (FeignException e){
                throw new BloqueioException();
            }
        }else{
            // checar se, caso exista o bloqueio, ele está ativo ou não.
            if(!bloqueios.getAtivo()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @Transactional
    public Bloqueios processaBloqueio(String ip, String userAgent, String id){
        Assert.notNull(ip, "Falha ao processar dados do cartão");
        Assert.notNull(id, "Falha ao processar dados do cartão");
        Assert.notNull(userAgent, "Falha ao processar dados do cartão");
        // false para o campo ativo!
        return new Bloqueios(id,userAgent,ip,false);
    }

}
