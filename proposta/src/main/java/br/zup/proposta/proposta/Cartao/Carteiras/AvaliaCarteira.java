package br.zup.proposta.proposta.Cartao.Carteiras;

import br.zup.proposta.proposta.Cartao.Model.Carteiras;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraClient;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.net.URI;

@Component
public class AvaliaCarteira {

    private CarteirasRequest request;

    private final Logger logger  = LoggerFactory.getLogger(AvaliaCarteira.class);

    public AvaliaCarteira(CarteirasRequest request){
        this.request = request;
    }


    @Transactional
    public ResponseEntity<?> novaCarteira(String id, EntityManager manager, CarteiraClient carteiraClient, UriComponentsBuilder builder) {

        // tenta inserir uma nova carteira na api externa e salvar no sistema.

        try {
            CarteiraResponse response =  carteiraClient.adicionaCarteira(id,request);
            logger.info(response.getResultado());
            Carteiras carteira = new Carteiras(request.getEmail(), request.getCarteira().toString());
            carteira.setIdCartao(id);
            manager.persist(carteira);
            URI location = builder.path("api/cartoes/"+id+"/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
            return ResponseEntity.created(location).build();

        }catch (FeignException e){
            return  ResponseEntity.unprocessableEntity().build();
        }catch (Exception e){
            /*
             * Por segurança, já que caso não seja possível acessar a rota
             * da api externa, o Feign acaba expondo  a url para o cliente
             */
            return  ResponseEntity.unprocessableEntity().build();
        }
    }
}
