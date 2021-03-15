package br.zup.proposta.proposta.ClientHttp.Bloqueio;

import br.zup.proposta.proposta.Jobs.JobDeAvaliacaoCartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${api.bloqueio}", name = "notificaBloqueio", fallbackFactory = ApiBloqueio.ApiBloqueioFallbackFactory.class)

public interface ApiBloqueio {

    final Logger logger  =  LoggerFactory.getLogger(JobDeAvaliacaoCartao.class);

    @PostMapping("/{id}/bloqueios")
    BloqueioResponse notificaBloqueio(@PathVariable("id") String id,BloqueioRequest request);


    @Component
    static class ApiBloqueioFallbackFactory implements FallbackFactory<ApiBloqueio> {
        @Override
        public ApiBloqueio create(Throwable cause) {

            String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

            return new ApiBloqueio() {
                @Override
                public BloqueioResponse notificaBloqueio(String id, BloqueioRequest request) {
                    logger.error(httpStatus);
                    return null;
                }
            };
        }
    }



}
