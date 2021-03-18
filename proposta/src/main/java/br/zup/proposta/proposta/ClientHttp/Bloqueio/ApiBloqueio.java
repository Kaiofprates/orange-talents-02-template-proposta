package br.zup.proposta.proposta.ClientHttp.Bloqueio;

import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${api.bloqueio}", name = "notificaBloqueio")

public interface ApiBloqueio {

    final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);

    @PostMapping("/{id}/bloqueios")
    BloqueioResponse notificaBloqueio(@PathVariable("id") String id,BloqueioRequest request);

}
