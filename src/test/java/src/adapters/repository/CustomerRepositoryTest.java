package src.adapters.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import src.adapters.repository.jpa.CustomerJpaRepository;
import src.adapters.repository.model.CustomerJpa;
import src.core.domain.models.Customer;
import src.core.domain.models.NewCustomerRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {


    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @InjectMocks
    private CustomerRepository customerRepository;

    @Mock
    private CustomerJpaRepository jpaRepository;

    @Test
    @DisplayName("Should to create a Customer")
    void shouldToCreateCustomer() {

        // cenary
        var expectedCustomerDomain = createExpectedCustomer();
        var expectedCustomerModel = createCustomerModel();
        var customerDomainForRelationalRepository = createCustomerDomain();

        ArgumentCaptor<CustomerJpa> customerModelCaptor = ArgumentCaptor.forClass(CustomerJpa.class);
        Mockito.when(jpaRepository.save(customerModelCaptor.capture())).thenReturn(expectedCustomerModel);

        // action
        var savedCustomer = customerRepository.save(customerDomainForRelationalRepository);

        // validation
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertEquals(expectedCustomerDomain.getId(), savedCustomer.getId());
        Assertions.assertEquals(expectedCustomerDomain.getName(), savedCustomer.getName());
        Assertions.assertEquals(expectedCustomerDomain.getDocument(), savedCustomer.getDocument());

    }


    private Customer createExpectedCustomer() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    private CustomerJpa createCustomerModel() {
        return new CustomerJpa(createExpectedCustomer());
    }

    private Customer createCustomerDomain() {
        return createCustomerRequest().toDomain();
    }

    private NewCustomerRequest createCustomerRequest() {
        return new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }


}