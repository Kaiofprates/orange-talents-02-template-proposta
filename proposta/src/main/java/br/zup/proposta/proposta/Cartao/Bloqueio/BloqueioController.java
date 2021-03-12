package br.zup.proposta.proposta.Cartao.Bloqueio;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class BloqueioController {

    @PostMapping("/cartoes/bloqueio/{id}")
    public String bloqueioDeCartao(@PathVariable("id") String id, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest userIp){
        return id + " ---- " + userAgent + " ---- " + userIp.getRemoteAddr() ;
    }

}
