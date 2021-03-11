package br.zup.proposta.proposta.ClientHttp.SolicitacaoCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${api.cartao}", name = "solicitacao")
public interface SolicitacaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "", consumes = "application/json")
    ResponseSolicitacao cadastra(@RequestBody SolicacaoRequest request);

}
