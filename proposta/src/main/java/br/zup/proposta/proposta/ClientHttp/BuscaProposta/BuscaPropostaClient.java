package br.zup.proposta.proposta.ClientHttp.BuscaProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${api.proposta}", name = "buscaProposta")
public interface BuscaPropostaClient {

    @GetMapping("/cartoes")
    BuscaPropostaResponse busca(@RequestParam(name = "idProposta") Long idProposta);
}
