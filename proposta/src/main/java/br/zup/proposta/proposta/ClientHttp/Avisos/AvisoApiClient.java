package br.zup.proposta.proposta.ClientHttp.Avisos;

import br.zup.proposta.proposta.Cartao.Avisos.AvisoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${api.bloqueio}", name = "notificaAviso")
public interface AvisoApiClient {

    @PostMapping("/{id}/avisos")
    AvisoResponse notificaAviso(@PathVariable("id") String id, AvisoApiRequest request);
}
