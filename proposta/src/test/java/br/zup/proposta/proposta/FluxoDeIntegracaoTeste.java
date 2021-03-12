package br.zup.proposta.proposta;

import br.zup.proposta.proposta.Proposta.Proposta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@AutoConfigureMockMvc
public class FluxoDeIntegracaoTeste {


    /**
     * 1 - testa a criação de uma proposta com sucesso
     * 2 -  testa o retorno da Location no Header
     * 3 - testa o ofuscamento do documento
     */

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    @DisplayName("Deve testar todo o fluxo de integração com apis externas")
    public void apiIntegracaoTest() throws Exception {
        String request = "{\n" +
                "\t\"documento\" : \"053.306.640-98\",\n" +
                "\t\"email\" : \"teste@teste.com.br\",\n" +
                "\t\"nome\" : \"User teste\",\n" +
                "\t\"endereco\" : {\n" +
                "\t\t\"logradouro\" : \"Rua teste\",\n" +
                "\t\t\"numero\" : \"1020\",\n" +
                "\t\t\"bairro\" : \"Bairo teste\",\n" +
                "\t\t\"cidade\" : \"Cidade teste\",\n" +
                "\t\t\"estado\" : \"Estado teste\",\n" +
                "\t\t\"cep\" : \"39401138\"\n" +
                "\t},\n" +
                "\t\"salario\" : \"2500\"\t\n" +
                "}";

        ResultActions chamada = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/propostas")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andDo(MockMvcResultHandlers.print());


        String location = chamada.andReturn().getResponse().getHeader("Location");

        ResultActions response =  mockMvc.perform(MockMvcRequestBuilders.get(location));

       response.andExpect(MockMvcResultMatchers.jsonPath("$.documento").value("***.***.***-98"));

    }

}
