package src.adapters.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationRestAssured {

    private static final String BASE_PATH = "/pegasus/api/v1";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        RestAssured.basePath = BASE_PATH;
        RestAssured.port = port;

    }

}
