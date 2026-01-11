package com.msaran.steps;

// 1. IMPORT DO CUCUMBER EM INGLÊS (Para usar @Given, @When)
import io.cucumber.java.en.*; 

// 2. REST ASSURED
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// 3. JUNIT 5 - O IMPORT CORRETO DAS ASSERÇÕES
import org.junit.jupiter.api.Assertions; 

import java.util.HashMap;
import java.util.Map;

public class Steps {

    private Map<String, Object> petPayload; 
    private Response response; 

    @io.cucumber.java.Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // O texto dentro das aspas pode ser em Português, sem problemas!
    @Given("que eu possuo os dados de um pet chamado {string} com status {string}")
    public void que_eu_possuo_os_dados_de_um_pet(String nomePet, String statusPet) {
        petPayload = new HashMap<>();
        petPayload.put("id", 123456); 
        petPayload.put("name", nomePet);
        petPayload.put("status", statusPet);

        Map<String, Object> category = new HashMap<>();
        category.put("id", 1);
        category.put("name", "Cachorros");
        petPayload.put("category", category);
    }

    @When("eu envio uma requisicao POST para o endpoint {string}")
    public void eu_envio_uma_requisicao_post(String endpoint) {
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(petPayload) 
                .when()
                .post(endpoint); 

        System.out.println("Retorno da API: " + response.asString());
    }

    @Then("o status code da resposta deve ser {int}")
    public void o_status_code_da_resposta_deve_ser(Integer statusCodeEsperado) {
        // Correção: Assertions do JUnit 5
        Assertions.assertEquals(statusCodeEsperado.intValue(), response.getStatusCode());
    }

    @Then("o corpo da resposta deve conter o ID do pet criado")
    public void o_corpo_da_resposta_deve_conter_o_id() {
        Number idRetornado = response.jsonPath().get("id");
        Assertions.assertNotNull(idRetornado, "O ID não deveria ser nulo");
    }

    @Then("o corpo da resposta deve conter o nome {string}")
    public void o_corpo_da_resposta_deve_conter_o_nome(String nomeEsperado) {
        String nomeRetornado = response.jsonPath().get("name");
        Assertions.assertEquals(nomeEsperado, nomeRetornado);
    }
}