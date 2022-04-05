package src.core.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import src.adapters.repository.CustomerRepository;
import src.core.domain.exception.CustomerNotFoundException;
import src.core.domain.models.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCustomerByIdUseCaseTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @InjectMocks
    private GetCustomerByIdUseCase useCase;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("Should to get a Customer By Id")
    void shouldToGetACustomerById() {

        // cenary
        var expectedCustomerDomain = createExpectedCustomerDomain();

        when(repository.getCustomerById(CUSTOMER_ID)).thenReturn(createExpectedCustomerDomain());

        // action
        var customerDomain = useCase.getCustomerById(CUSTOMER_ID);

        // validation
        assertNotNull(customerDomain);
        assertEquals(expectedCustomerDomain.getId(), customerDomain.getId());
        assertEquals(expectedCustomerDomain.getName(), customerDomain.getName());
        assertEquals(expectedCustomerDomain.getDocument(), customerDomain.getDocument());

    }

    @Test
    @DisplayName("Don't Should to get a Customer By Id")
    void dontShouldToGetACustomerById() {

        // cenary
        var invalidCustomerId = 2L;

        when(repository.getCustomerById(invalidCustomerId)).thenThrow(CustomerNotFoundException.class);

        // action
        var exception = assertThrows(CustomerNotFoundException.class,
                () -> useCase.getCustomerById(invalidCustomerId));

        // validation
        assertEquals(CustomerNotFoundException.class, exception.getClass());

    }

    private Customer createExpectedCustomerDomain() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }


}