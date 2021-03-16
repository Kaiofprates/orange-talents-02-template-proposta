package br.zup.proposta.proposta.Cartao.Avisos;

import br.zup.proposta.proposta.Cartao.Model.Avisos;
import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.ClientHttp.Avisos.AvisoApiClient;
import br.zup.proposta.proposta.ClientHttp.Avisos.AvisoApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartoes")
public class AvisoController {


    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AvisoApiClient avisoApiClient;

    @PostMapping("/avisos/{id}")
    @Transactional
    public ResponseEntity<?> novoAviso(@PathVariable("id") String id,
                                    @Valid
                            @RequestBody AvisoRequest request,
                                    @RequestHeader(value = "User-Agent") String userAgent,
                                    HttpServletRequest userIp){


        Cartao cartao = manager.find(Cartao.class,id);
        if(cartao == null)
            return  ResponseEntity.notFound().build();


        Avisos novoAviso = new Avisos(request.getValidoAte(), request.getDestino(),userIp.getRemoteAddr(),userAgent,id);
        Assert.notNull(novoAviso,"Não foi possível criar solicitação de novo aviso");
        cartao.setAvisos(novoAviso);

        /*
        * primeira implementação funcional
        * no futuro refatore!
        */

        try{
            AvisoApiRequest apiRequest = new AvisoApiRequest(request.getDestino(), request.getValidoAte().toString());
            avisoApiClient.notificaAviso(id,apiRequest);
            manager.persist(novoAviso);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok().build();

    }

}
