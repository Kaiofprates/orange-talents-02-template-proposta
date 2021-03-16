package br.zup.proposta.proposta.Cartao.Carteiras;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/cartoes")
public class CarteirasController {



    @PostMapping("/carteiras/{id}")
    public ResponseEntity<?> novaCarteira(@PathVariable("id") String id, @RequestBody @Valid CarteirasRequest request, UriComponentsBuilder response){



        URI location = response.path("api/cartoes/"+id+"/carteiras/{id}").buildAndExpand("123123123").toUri();

        return ResponseEntity.created(location).build();
    }

}
