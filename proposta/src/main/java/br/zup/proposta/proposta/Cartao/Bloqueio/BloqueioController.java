package br.zup.proposta.proposta.Cartao.Bloqueio;

import br.zup.proposta.proposta.Cartao.Model.Bloqueios;
import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.ApiBloqueio;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioRequest;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.BloqueioStatus;
import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import br.zup.proposta.proposta.Validacao.Exceptions.BloqueioException;
import feign.FeignException;
import javassist.tools.web.BadHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ApiBloqueio apiBloqueio;


    @PostMapping("/cartoes/bloqueio/{id}")
    @Transactional
    public ResponseEntity<?> bloqueioDeCartao(@PathVariable("id") String id,
                                              @RequestHeader(value = "User-Agent") String userAgent,
                                              HttpServletRequest userIp) throws BloqueioException {

        Cartao cartao = manager.find(Cartao.class,id);
        if(cartao == null)
            return ResponseEntity.notFound().build();

        String ip = userIp.getRemoteAddr();

        AvaliaBloqueio avaliaBloqueio = new AvaliaBloqueio(id,ip,userAgent,cartao);

        return avaliaBloqueio.autorizaBloqueio(manager,apiBloqueio);
    }




}
