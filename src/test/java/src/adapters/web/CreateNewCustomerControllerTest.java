package src.adapters.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import src.core.domain.models.Customer;
import src.core.domain.models.NewCustomerRequest;
import src.core.usecase.RegisterNewCustomerUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateNewCustomerControllerTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";
    private static final String HEADER_LOCATION_EXPECTED = "/customers/1";

    @InjectMocks
    private CreateNewCustomerController controller;

    @Mock
    private RegisterNewCustomerUseCase useCase;

    @Test
    @DisplayName("Should to create Customer")
    void shouldToCreateCustomer() {

        // cenary
        var newCustomerRequest = createNewCustomerRequest();
        var expectedCustomerDomain = createExpectedCustomerDomain();

        var uriComponentsBuilder = UriComponentsBuilder.newInstance();

        Mockito.when(useCase.registerNewCustomer(Mockito.any(NewCustomerRequest.class))).thenReturn(expectedCustomerDomain);

        // action
        var response = controller.createCustomer(newCustomerRequest, uriComponentsBuilder);

        // validation
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertEquals(HEADER_LOCATION_EXPECTED, getHeaderLocationByResponse(response));
    }

    private NewCustomerRequest createNewCustomerRequest() {
        return new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }

    private Customer createExpectedCustomerDomain() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    private String getHeaderLocationByResponse(ResponseEntity<?> response) {
        return response.getHeaders().get("Location").stream().findFirst().get();
    }

}