package br.zup.proposta.proposta.ClientHttp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:9999/api/solicitacao", name = "solicitacao")
public interface SolicitacaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "", consumes = "application/json")
    ResponseSolicitacao cadastra(@RequestBody SolicacaoRequest request);

}
