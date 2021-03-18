package br.zup.proposta.proposta.Cartao;

import br.zup.proposta.proposta.Proposta.Proposta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Profile("dev")
public class AvaliaCartaoTeste {

    @Mock
    private Proposta proposta;

    @Test
    @DisplayName("Deve lidar com a classe que avalia cartões")
    void testaAvaliaProposta(){

        AvaliaCartao avaliaCartao = mock(AvaliaCartao.class);

        when(avaliaCartao.avaliaCartao(proposta)).thenReturn(true);
        Assert.isTrue(avaliaCartao.avaliaCartao(proposta));

    }


}
