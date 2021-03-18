package br.zup.proposta.proposta.ClientHttp.Carteiras;

import br.zup.proposta.proposta.Cartao.Carteiras.CarteirasRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "${api.bloqueio}", name = "adicionaCarteira")
public interface CarteiraClient {
    @PostMapping("/{id}/carteiras")
    CarteiraResponse adicionaCarteira(@PathVariable("id") String id, @RequestBody @Valid CarteirasRequest request);
}
