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
import src.core.domain.exception.CustomerNotFoundException;
import src.core.domain.models.Customer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        var customerJpaForReturnMockedRepository = createCustomerModel();

        var customerDomainForRepository = createCustomerDomain();

        var expectedCustomerDomain = createCustomerDomain();

        ArgumentCaptor<CustomerJpa> customerModelCaptor = ArgumentCaptor.forClass(CustomerJpa.class);
        Mockito.when(jpaRepository.save(customerModelCaptor.capture()))
                .thenReturn(customerJpaForReturnMockedRepository);

        // action
        var savedCustomer = customerRepository.save(customerDomainForRepository);

        // validation
        assertNotNull(savedCustomer);
        assertEquals(expectedCustomerDomain.getId(), savedCustomer.getId());
        assertEquals(expectedCustomerDomain.getName(), savedCustomer.getName());
        assertEquals(expectedCustomerDomain.getDocument(), savedCustomer.getDocument());

    }

    @Test
    @DisplayName("Don't should to create a Customer when they are Null")
    void dontShouldToCreateACustomer() {

        // cenary
        Customer invalidCustomer = null;

        // action
        var exception = Assertions.assertThrows(NullPointerException.class,
                () -> customerRepository.save(invalidCustomer));

        // validation
        assertEquals(NullPointerException.class, exception.getClass());

    }

    @Test
    @DisplayName("Don't should to create a Customer when they properties are invalid")
    void dontShouldToCreateACustomerWithInvalidProperties() {

        // cenary
        var customerDomain = createCustomerDomain();

        ArgumentCaptor<CustomerJpa> customerModelCaptor = ArgumentCaptor.forClass(CustomerJpa.class);
        Mockito.when(jpaRepository.save(customerModelCaptor.capture()))
                .thenThrow(IllegalArgumentException.class);

        // action
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> customerRepository.save(customerDomain));

        // validation
        assertEquals(IllegalArgumentException.class, exception.getClass());

    }

    @Test
    @DisplayName("Should to get a Customer by Id")
    void shouldToGetACustomerById() {

        // cenary
        var expectedCustomerJpa = Optional.of(createCustomerModel());

        Mockito.when(jpaRepository.findById(CUSTOMER_ID)).thenReturn(expectedCustomerJpa);

        // action
        var customerFound = customerRepository.getCustomerById(CUSTOMER_ID);

        // validation
        assertNotNull(customerFound);
        assertEquals(CUSTOMER_ID, customerFound.getId());
        assertEquals(CUSTOMER_NAME, customerFound.getName());
        assertEquals(CUSTOMER_DOCUMENT, customerFound.getDocument());

    }

    @Test
    @DisplayName("Don't should to get a Customer when Id as invalid")
    void dontShouldToGetACustomerById() {

        // cenary
        Mockito.when(jpaRepository.findById(CUSTOMER_ID)).thenThrow(CustomerNotFoundException.class);

        // action
        var exception = Assertions.assertThrows(CustomerNotFoundException.class,
                () -> customerRepository.getCustomerById(CUSTOMER_ID));

        // validation
        assertEquals(CustomerNotFoundException.class, exception.getClass());

    }

    private CustomerJpa createCustomerModel() {
        return new CustomerJpa(createCustomerDomain());
    }

    private Customer createCustomerDomain() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }
}