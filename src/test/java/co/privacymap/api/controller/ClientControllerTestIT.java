package co.privacymap.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ClientControllerTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup(){
        RestAssured.port = port;
    }

    @Test
    public void testCreateClient(){
        String newClientJSON = "{\n" +
                "    \"name\": \"Bruno Silveira\",\n" +
                "    \"cpf\" : \"07481573018\",\n" +
                "    \"city\": \"Valinhos-SP\",\n" +
                "    \"email\": \"bruno@teste.com.br\",\n" +
                "    \"password\": \"Senha123456\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(newClientJSON)
                .post("/clients")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Bruno Silveira"));
    }
}
