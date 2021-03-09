package br.zup.proposta.proposta.ClientHttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoClient client;

    @PostMapping("/solicitacao")
    public String cadastra(@RequestBody SolicacaoRequest request){
        return client.cadastra(request).getResultadoSolicitacao().toString();
    }


}
