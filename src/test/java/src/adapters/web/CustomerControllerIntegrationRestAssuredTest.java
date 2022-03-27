package src.adapters.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import src.core.domain.models.NewCustomerRequest;

import static io.restassured.RestAssured.expect;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationRestAssuredTest {

    private static final String BASE_PATH = "/pegasus/api/v1";

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.basePath = BASE_PATH;
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Rest Assured Simple Test - Should create a new Customer and return HTTP Status 201 - Created")
    void shouldCreateNewCustomerSimpleTest() throws JsonProcessingException {

        RestAssured
                .given()
                .log().all()
                .contentType(APPLICATION_JSON.toString())
                .when()
                .log().all()
                .body(createNewCustomerJsonRequest())
                .post("/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    private String createNewCustomerJsonRequest() throws JsonProcessingException {
        var jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(
                new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT)
        );
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

        RestAssured
                .when()
                .get(response.header("Location"))
                .then()
                .log().all()
                .spec(
                        expect()
                                .header("content-type", Matchers.is("application/json"))
                                .body("path", Matchers.is(Matchers.equalTo(BASE_PATH + "/customers/" + CUSTOMER_ID)))
                );


    }

}