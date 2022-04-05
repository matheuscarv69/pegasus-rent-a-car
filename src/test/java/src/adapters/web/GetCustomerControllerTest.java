package src.adapters.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import src.core.domain.exception.CustomerNotFoundException;
import src.core.domain.models.Customer;
import src.core.usecase.GetCustomerByIdUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetCustomerControllerTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @InjectMocks
    private GetCustomerController controller;

    @Mock
    private GetCustomerByIdUseCase useCase;

    @Test
    @DisplayName("Should To Return A Customer By ID")
    void shouldToReturnACustomerById() {

        // cenary

        Mockito.when(useCase.getCustomerById(CUSTOMER_ID)).thenReturn(createCustomer());

        // action
        var customer = controller.getCustomerById(CUSTOMER_ID).getBody();

        // validation
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals(CUSTOMER_NAME, customer.getName());
        assertEquals(CUSTOMER_DOCUMENT, customer.getDocument());

    }

    private Customer createCustomer() {

        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();

    }

    @Test
    @DisplayName("Should throw CustomerNotFoundException")
    void shouldThrowCustomerNotFoundException() {

        // cenary
        var invalidCustomerId = 2L;

        Mockito.when(useCase.getCustomerById(invalidCustomerId)).thenThrow(CustomerNotFoundException.class);

        // action
        var customerNotFoundException = Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> controller.getCustomerById(invalidCustomerId)
        );

        // validation
        assertNotNull(customerNotFoundException.getClass());
        assertEquals(CustomerNotFoundException.class, customerNotFoundException.getClass());

    }


}