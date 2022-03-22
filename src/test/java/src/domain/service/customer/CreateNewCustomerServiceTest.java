package src.domain.service.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import src.application.controllers.requests.NewCustomerRequest;
import src.domain.entity.Customer;
import src.infrastructure.repository.relationals.RelationalCustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateNewCustomerServiceTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @InjectMocks
    private CreateNewCustomerService service;

    @Mock
    private RelationalCustomerRepository repository;

    @Test
    @DisplayName("Should to create a new Customer")
    void shouldToCreateNewCustomer() {

        // cenary
        var expectedCustomer = createExpectedCustomer();
        var customerDomainForService = createCustomerDomain();

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        Mockito.when(repository.create(customerCaptor.capture())).thenReturn(expectedCustomer);

        // action
        var savedCustomer = service.createCustomer(customerDomainForService);

        // validation
        assertNotNull(savedCustomer);
        assertEquals(expectedCustomer.getId(), savedCustomer.getId());
        assertEquals(expectedCustomer.getName(), savedCustomer.getName());
        assertEquals(expectedCustomer.getDocument(), savedCustomer.getDocument());

    }

    private Customer createExpectedCustomer() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    private Customer createCustomerDomain() {
        return createCustomerRequest().toDomain();
    }

    private NewCustomerRequest createCustomerRequest() {
        return new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }
}