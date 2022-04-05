package src.adapters.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import src.adapters.repository.jpa.CustomerJpaRepository;
import src.core.domain.models.NewCustomerRequest;
import src.core.domain.models.StandardError;

import static io.restassured.RestAssured.expect;
import static org.springframework.http.MediaType.APPLICATION_JSON;

class CreateNewCustomerControllerIntegrationRestAssuredTest extends BaseIntegrationRestAssured {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @Test
    @DisplayName("Rest Assured Simple Test - Should create a new Customer and return HTTP Status 201 - Created")
    void shouldCreateNewCustomerSimpleTest() throws JsonProcessingException {

        RestAssured
                .given()
                .contentType(APPLICATION_JSON.toString())
                .when()
                .log().all()
                .body(createNewCustomerJsonRequest())
                .post("/customers")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Rest Assured Getting Response Test - Should create a new Customer and return HTTP Status 201 - Created")
    void shouldCreateNewCustomerGettingResponseTest() throws JsonProcessingException {

        var response = RestAssured
                .given()
                .contentType(APPLICATION_JSON.toString())
                .when()
                .body(createNewCustomerJsonRequest())
                .post("/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract();

        var expectedPath = "/pegasus/api/v1/customers/" + CUSTOMER_ID;

        Assertions.assertTrue(response.header("Location").contains(expectedPath),
                "This path is invalid " + response.header("Location")
        );

    }

    @Test
    @DisplayName("Rest Assured - Don't should to create a new Customer with empty fields and return HTTP Status 400 - Bad Request")
    void dontShouldCreateNewCustomerWithEmptyFields() throws JsonProcessingException {

        RestAssured
                .given()
                .contentType(APPLICATION_JSON.toString())
                .when()
                .log().all()
                .body(createCustomerRequestWithEmptyFields())
                .post("/customers")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .assertThat()
                .body("errors.name", Matchers.equalTo("não deve estar em branco"));

    }

    @Test
    @DisplayName("Rest Assured - Don't should to create a new Customer With Invalid Fields and return HTTP Status 400 - Bad Request")
    void dontShouldCreateNewCustomerWithInvalidFields() throws JsonProcessingException {

        RestAssured
                .given()
                .contentType(APPLICATION_JSON.toString())
                .when()
                .log().all()
                .body(createCustomerRequestWithInvalidFields())
                .post("/customers")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .assertThat()
                .body("errors.document", Matchers.equalTo("tamanho deve ser de 11 caracteres"))
                .body("errors.name", Matchers.equalTo("não deve estar em branco"));

    }

    private String createNewCustomerJsonRequest() throws JsonProcessingException {

        var jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(
                new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT)
        );

    }

    private String createCustomerRequestWithEmptyFields() throws JsonProcessingException {

        var jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(
                new NewCustomerRequest("", "")
        );

    }

    private String createCustomerRequestWithInvalidFields() throws JsonProcessingException {

        var jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(
                new NewCustomerRequest(null, "123456789101")
        );

    }

}