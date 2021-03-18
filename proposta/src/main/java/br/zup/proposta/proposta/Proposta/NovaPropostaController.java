package br.zup.proposta.proposta.Proposta;

import br.zup.proposta.proposta.Cartao.AvaliaCartao;
import br.zup.proposta.proposta.Validacao.Exceptions.DuplicateDocumentoException;
import io.micrometer.core.annotation.Counted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> retornaProposta(@PathVariable("id") Long id){
        Optional<Proposta> proposta = repository.findById(id);

        if(proposta.isPresent()){
            PropostaResponse response = new PropostaResponse(proposta.get());
            Assert.notNull(response,"Erro ao retornar dados da proposta");
            return  ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }


}
