package br.zup.proposta.proposta.Proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager manager;
    // 1

    @PostMapping("/propostas")
    @Transactional
    // 2
    public ResponseEntity<?> create(@RequestBody  @Valid PropostaRequest request, UriComponentsBuilder response){

        //3
        Proposta proposta = request.toModel();

        manager.persist(proposta);

        // 4
        Assert.isTrue(proposta != null, "Não foi possível concluir sua proposta");

        // 5
        URI location = response.path("api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
