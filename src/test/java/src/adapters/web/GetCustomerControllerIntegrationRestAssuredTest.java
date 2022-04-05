package src.adapters.web;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class GetCustomerControllerIntegrationRestAssuredTest extends BaseIntegrationRestAssured {

    private static final Long CUSTOMER_ID = 100L;

    @Test
    @DisplayName("Rest Assured - Should to get a Customer by ID - Statuscode 200-ok")
    void shouldToGetACustomerById() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("customerId", CUSTOMER_ID)
                .when()
                .get("/customers/{customerId}")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("Rest Assured - Don't Should to get a Customer by ID when Customer Not Found - Statuscode 404-ok")
    void dontShouldToGetACustomerById() {

        var invalidCustomerId = 101L;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("customerId", invalidCustomerId)
                .when()
                .get("/customers/{customerId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

}