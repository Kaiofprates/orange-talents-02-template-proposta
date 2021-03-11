package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.Cartao.Model.Cartao;
import br.zup.proposta.proposta.Cartao.Model.CartaoRepository;
import br.zup.proposta.proposta.Cartao.Model.Digital;
import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import br.zup.proposta.proposta.Validacao.Exceptions.FingerPrintException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    private  final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);


    @PostMapping("/cartoes/{id}")
    public ResponseEntity<?> cadastraFingerprint(@PathVariable("id") String id,
                                                 @RequestBody @Valid BiometriaRequest request,
                                                 UriComponentsBuilder response) throws FingerPrintException {
        request.isValid();

        if(!cartaoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if(cartao.isPresent()){
            Digital digital = new Digital(id,request.getFingerprint());
            Cartao update = cartao.get();
            update.setDigitais(digital);
            List<Digital> digitais = update.getDigitais();
            logger.info(update.getDigitais().get(digitais.size() - 1).toString());
        }

        URI location = response.path("api/cartoes/"+id+"/biometria/{id}").buildAndExpand(12).toUri();
        return ResponseEntity.created(location).build();
    }


}
