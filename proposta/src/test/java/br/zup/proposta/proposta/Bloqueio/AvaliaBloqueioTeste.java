package br.zup.proposta.proposta.Bloqueio;

import br.zup.proposta.proposta.Cartao.Bloqueio.AvaliaBloqueio;
import br.zup.proposta.proposta.ClientHttp.Bloqueio.ApiBloqueio;
import br.zup.proposta.proposta.Validacao.Exceptions.BloqueioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;


@SpringBootTest
@Profile("dev")
public class AvaliaBloqueioTeste {

    @Mock
    private EntityManager manager;

    @Mock
    private ApiBloqueio apiBloqueio;


    @Test
    @DisplayName("Deve lidar com a classe que avalia bloqueios")
    void avaliaBloqueioTeste() throws BloqueioException {

        AvaliaBloqueio avaliaBloqueio = mock(AvaliaBloqueio.class);
        when(avaliaBloqueio.autorizaBloqueio(manager,apiBloqueio))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));

        Assertions.assertEquals(avaliaBloqueio.autorizaBloqueio(manager,apiBloqueio), new ResponseEntity<>(HttpStatus.OK));
        Assertions.assertEquals(avaliaBloqueio.autorizaBloqueio(manager,apiBloqueio), new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));

    }


}
