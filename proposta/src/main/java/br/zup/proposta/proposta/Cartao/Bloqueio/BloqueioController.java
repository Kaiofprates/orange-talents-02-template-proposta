package br.zup.proposta.proposta.Cartao.Bloqueio;

import br.zup.proposta.proposta.Cartao.Model.Bloqueios;
import br.zup.proposta.proposta.Cartao.Model.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BloqueioController {

    @PersistenceContext
    private EntityManager manager;


    /*
    * Primeira implementação funcional,
    *
    * por hora toda a lógica funciona.
    * Se atente ao fato de não termos nada para retonar necessariamente um bad request
    * por isso ele acontece caso nada... absolutamente nada dê certo.
    *
    * KAIO DO FUTURO, POR FAVOR REFATORE ISSO!
    *
    */



    @PostMapping("/cartoes/bloqueio/{id}")
    @Transactional
    public ResponseEntity<?> bloqueioDeCartao(@PathVariable("id") String id, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest userIp){


        Cartao cartao = manager.find(Cartao.class,id);
        if(cartao == null)
            return ResponseEntity.notFound().build();

        String ip = userIp.getRemoteAddr();
        /*
        * 1 - se entre os bloqueios existe um com o id do cartão e status true
        */
        Bloqueios bloqueios = manager.find(Bloqueios.class,id);

        if(bloqueios == null){
            /*
            * 2 - caso o bloqueio estiver nulo para o id do cartão, siga a regra de negócio
            */
            manager.persist(processaBloqueio(ip,userAgent,id));
            return ResponseEntity.ok().build();
        }else{
            if(!bloqueios.getAtivo()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }



    public Bloqueios processaBloqueio(String ip, String userAgent, String id){

        Assert.notNull(ip, "Falha ao processar dados do cartão");
        Assert.notNull(id, "Falha ao processar dados do cartão");
        Assert.notNull(userAgent, "Falha ao processar dados do cartão");

        // false para o campo ativo!

        return new Bloqueios(id,userAgent,ip,false);

    }


}
