package br.zup.proposta.proposta.Cartao.Carteiras;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.Cartao.Model.Carteiras;
import br.zup.proposta.proposta.Cartao.Model.Repository.CarteirasRepository;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartoes")
public class CarteirasController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CarteiraClient carteiraClient;

    @Autowired
    private CarteirasRepository repository;

    private final Logger logger  = LoggerFactory.getLogger(AvaliaCarteira.class);



    @PostMapping("/carteiras/{id}")
    @Transactional
    public ResponseEntity<?> novaCarteira(@PathVariable("id") String id, @RequestBody @Valid CarteirasRequest request, UriComponentsBuilder builder){

        Cartao cartao = manager.find(Cartao.class,id);
        if(cartao == null) return ResponseEntity.notFound().build();

        // avalia se já existe um cartão associado à mesma carteira.

        Optional<Carteiras> carteiras = repository.findByIdcartaoAndEmissor(id,request.getCarteira().toString());
        if(carteiras.isPresent())
            return  ResponseEntity.unprocessableEntity().build();


        AvaliaCarteira avaliaCarteira = new AvaliaCarteira(request);

       return avaliaCarteira.novaCarteira(id, manager,carteiraClient,builder);

    }

}
