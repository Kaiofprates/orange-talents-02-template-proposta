package br.zup.proposta.proposta.Cartao.Biometria;

import br.zup.proposta.proposta.Cartao.Model.CartaoRepository;
import br.zup.proposta.proposta.Validacao.Exceptions.FingerPrintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    /*
     * No futuro melhorar  a lógica na recuração do id da digital
     * Talvez, a estratégia de lançar excessões custumizadas com o status http
     * não seja uma boa ideia ( verificar com instrutores )
     */

    @PostMapping("/cartoes/{id}")
    public ResponseEntity<?> cadastraFingerprint(@PathVariable("id") String id,
                                                 @RequestBody @Valid BiometriaRequest request,
                                                 UriComponentsBuilder response) throws FingerPrintException {

        // estoura uma excessão caso não seja possível fazer o decode para base64
        request.isValid();

        if(!cartaoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        // retona o id da digital, ou retorna null em caso de falha.
        Long digitalID = request.salvaDigital(cartaoRepository,id);

        Assert.notNull(digitalID,"Falha ao recuperar informações de biometria");

        URI location = response.path("api/cartoes/"+id+"/biometria/{id}").buildAndExpand(digitalID).toUri();
        return ResponseEntity.created(location).build();
    }


}
