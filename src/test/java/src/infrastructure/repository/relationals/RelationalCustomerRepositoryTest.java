package src.infrastructure.repository.relationals;

import org.junit.jupiter.api.Assertions;
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
import src.infrastructure.repository.models.CustomerModel;

@ExtendWith(MockitoExtension.class)
class RelationalCustomerRepositoryTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @InjectMocks
    private RelationalCustomerRepository relationalRepository;

    @Mock
    private CustomerRepositoryJPA jpaRepository;

    @Test
    @DisplayName("Should to create a Customer")
    void shouldToCreateCustomer() {

        // cenary
        var expectedCustomerDomain = createExpectedCustomer();
        var expectedCustomerModel = createCustomerModel();
        var customerDomainForRelationalRepository = createCustomerDomain();

        ArgumentCaptor<CustomerModel> customerModelCaptor = ArgumentCaptor.forClass(CustomerModel.class);
        Mockito.when(jpaRepository.save(customerModelCaptor.capture())).thenReturn(expectedCustomerModel);

        // action
        var savedCustomer = relationalRepository.create(customerDomainForRelationalRepository);

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

    private CustomerModel createCustomerModel() {
        return new CustomerModel(createExpectedCustomer());
    }

    private Customer createCustomerDomain() {
        return createCustomerRequest().toDomain();
    }

    private NewCustomerRequest createCustomerRequest() {
        return new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }

}