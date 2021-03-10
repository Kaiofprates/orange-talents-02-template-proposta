package br.zup.proposta.proposta.Proposta;

import br.zup.proposta.proposta.Cartao.AvaliaCartao;
import br.zup.proposta.proposta.Validacao.Exceptions.DuplicateDocumentoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class NovaPropostaController {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private AvaliaCartao avaliaCartao;

    @PostMapping("/propostas")
    public ResponseEntity<?> create(@RequestBody  @Valid PropostaRequest request, UriComponentsBuilder response) throws DuplicateDocumentoException {

        Proposta proposta = repository.save(request.toModel(repository));

        Assert.isTrue(proposta != null, "Não foi possível concluir sua proposta");

        avaliaCartao.avaliaCartao(proposta);

        URI location = response.path("api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
