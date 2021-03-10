package br.zup.proposta.proposta.NovaPropostaTest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
public class PropostaController {


    @Autowired
    private MockMvc mockMvc;


    @ParameterizedTest
    @MethodSource("requests")
    @DisplayName(" Deve lidar com formatos inválidos para o controller de propostas")
    public  void testFailController(String request) throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/propostas")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    public static Stream<Arguments> requests(){
      return Stream.of(
              Arguments.of("{\n" +
                      "\t\"documento\" : \"253.490.160-59\",\n" +
                      "\t\"email\" : \"john.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \"\",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"City\",\n" +
                      "\t\t\"estado\" : \"MG\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"1000\"\t\n" +
                      "}"),
              Arguments.of("{\n" +
                      "\t\"documento\" : \"\",\n" +
                      "\t\"email\" : \"john.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \"\",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"\",\n" +
                      "\t\t\"estado\" : \"MG\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"1000\"\t\n" +
                      "}"),
              Arguments.of("{\n" +
                      "\t\"documento\" : \"59417362070\",\n" +
                      "\t\"email\" : \"john.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \"\",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"City\",\n" +
                      "\t\t\"estado\" : \"MG\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"1000\"\t\n" +
                      "}"),
              Arguments.of("{\n" +
                      "\t\"documento\" : \"59417362070\",\n" +
                      "\t\"email\" : \"john.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \"\",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"City\",\n" +
                      "\t\t\"estado\" : \"MG\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"-1\"\t\n" +
                      "}"),
              Arguments.of("{\n" +
                      "\t\"documento\" : \"60.988.927/0001-03\",\n" +
                      "\t\"email\" : \"john.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \" 123 \",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"City\",\n" +
                      "\t\t\"estado\" : \"MG\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"1000\"\t\n" +
                      "}"),
              Arguments.of("{\n" +
                      "\t\"documento\" : \"60.988.927/0001-03\",\n" +
                      "\t\"email\" : \"john5@mail.com\",\n" +
                      "\t\"nome\" : \"John Doe\",\n" +
                      "\t\"endereco\" : {\n" +
                      "\t\t\"logradouro\" : \"Baker\",\n" +
                      "\t\t\"numero\" : \" 123 \",\n" +
                      "\t\t\"bairro\" : \"Vila\",\n" +
                      "\t\t\"cidade\" : \"City\",\n" +
                      "\t\t\"estado\" : \"\",\n" +
                      "\t\t\"cep\" : \"999999\"\n" +
                      "\t},\n" +
                      "\t\"salario\" : \"1000\"\t\n" +
                      "}")
              );
    };


    @ParameterizedTest
    @MethodSource("sucess")
    @DisplayName("Deveria lidar com sucesso ao cadastrar cpj e cnpj")
    public void testSucess(String request) throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/propostas")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    public static Stream<Arguments> sucess(){
        return Stream.of(
                Arguments.of("{\n" +
                        "\t\"documento\" : \"754.147.690-01\",\n" +
                        "\t\"email\" : \"john3@mail.com\",\n" +
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
                        "}"),
                Arguments.of("{\n" +
                        "\t\"documento\" : \"30.038.243/0001-80\",\n" +
                        "\t\"email\" : \"john2@mail.com\",\n" +
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
                        "}")
        );
    };


}
