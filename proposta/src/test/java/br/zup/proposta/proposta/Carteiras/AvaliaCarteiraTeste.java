package br.zup.proposta.proposta.Carteiras;

import br.zup.proposta.proposta.Cartao.Carteiras.AvaliaCarteira;
import br.zup.proposta.proposta.ClientHttp.Carteiras.CarteiraClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.EntityManager;

import java.net.URI;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@Profile("dev")
public class AvaliaCarteiraTeste {

    @Mock
    private EntityManager manager;

    @Mock
    private CarteiraClient carteiraClient;

    @Mock
    private UriComponentsBuilder builder;



    @Test
    @DisplayName("Deve testar o comportamento da classe que avalia carteiras")
    void avaliaCarteiraTeste(){

        AvaliaCarteira avaliaCarteira = mock(AvaliaCarteira.class);

        when(avaliaCarteira.novaCarteira("9607-7912-8652-8173",manager,carteiraClient,builder))
                .thenReturn( new ResponseEntity<>(HttpStatus.CREATED));
        when(avaliaCarteira.novaCarteira("9607-7912-8652-8171",manager,carteiraClient,builder))
                .thenReturn( new ResponseEntity<>(HttpStatus.NOT_FOUND));
        when(avaliaCarteira.novaCarteira("9607-7912-8652-8172",manager,carteiraClient,builder))
                .thenReturn( new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));

        Assertions.assertEquals(avaliaCarteira.novaCarteira("9607-7912-8652-8173",manager,carteiraClient,builder),new ResponseEntity<>(HttpStatus.CREATED));
        Assertions.assertEquals(avaliaCarteira.novaCarteira("9607-7912-8652-8172",manager,carteiraClient,builder),new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        Assertions.assertEquals(avaliaCarteira.novaCarteira("9607-7912-8652-8171",manager,carteiraClient,builder),new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
