package br.zup.proposta.proposta.Cartao.Avisos;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartoes")
public class AvisoController {

    @PostMapping("/avisos/{id}")
    public String novoAviso(@PathVariable("id") String id,
                            @Valid
                            @RequestBody AvisoRequest request,
                            @RequestHeader(value = "User-Agent") String userAgent,
                            HttpServletRequest userIp){


        request.setIpCliente(userIp.getRemoteAddr());
        request.setUserAgent(userAgent);

        return request.toString();

    }

}
