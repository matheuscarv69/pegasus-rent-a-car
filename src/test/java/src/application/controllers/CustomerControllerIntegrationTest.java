package src.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import src.application.controllers.requests.NewCustomerRequest;
import src.domain.entity.Customer;
import src.domain.service.customer.CreateNewCustomerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerIntegrationTest {

    private static final String URL = "/customers";

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateNewCustomerService service;

    @Test
    @DisplayName("Should create a new Customer and return HTTP Status 201 - Created")
    void createNewCustomerTest() throws Exception {

        // cenary
        Mockito.doReturn(createNewCustomer())
                .when(service)
                .createCustomer(Mockito.any(Customer.class));

        // action / validation
        mockMvc.perform(
                        post(URL)
                                .content(createNewCustomerJsonRequest())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(header().stringValues("Location","http://localhost/customers/1"));

    }

    private Customer createNewCustomer() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    private String createNewCustomerJsonRequest() throws JsonProcessingException {
        var jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(
                new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT)
        );
    }


}