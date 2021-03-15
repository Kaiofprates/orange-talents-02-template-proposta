package br.zup.proposta.proposta.NovaPropostaTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Profile("dev")
public class DuplicateDocumentoTeste {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deveria lidar com tentativas de cadastro de CPF ou CNPJ duplicados")
    public void exceptionDocumentoTest() throws Exception {

        String request = "{\n" +
                "\t\"documento\" : \"60.988.927/0001-03\",\n" +
                "\t\"email\" : \"john1@mail.com\",\n" +
                "\t\"nome\" : \"John Doe\",\n" +
                "\t\"endereco\" : {\n" +
                "\t\t\"logradouro\" : \"Baker\",\n" +
                "\t\t\"numero\" : \"123\",\n" +
                "\t\t\"bairro\" : \"Vila\",\n" +
                "\t\t\"cidade\" : \"City\",\n" +
                "\t\t\"estado\" : \"MG\",\n" +
                "\t\t\"cep\" : \"999999\"\n" +
                "\t},\n" +
                "\t\"salario\" : \"1000\"\t\n" +
                "}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/propostas")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/propostas")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andDo(MockMvcResultHandlers.print());

    }

}
