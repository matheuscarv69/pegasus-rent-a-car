package src.infrastructure.repository.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.domain.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerModelTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @Test
    @DisplayName("Should to generate Customer Model by Perfect Customer Domain")
    void shouldToGenerateCustomerModelTest() {

        // cenary
        var expectedCustomerModel = new CustomerModel(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_DOCUMENT);

        // action
        var customerModel = new CustomerModel(createCustomerDomain());

        // validation
        assertEquals(expectedCustomerModel.getId(), customerModel.getId());
        assertEquals(expectedCustomerModel.getName(), customerModel.getName());
        assertEquals(expectedCustomerModel.getDocument(), customerModel.getDocument());

    }

    private Customer createCustomerDomain() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    @Test
    @DisplayName("Should to generate Customer Model by Customer Domain without id")
    void shouldToGenerateCustomerModelByDomainWithoutIdTest() {

        // cenary
        var expectedCustomerModel = new CustomerModel(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_DOCUMENT);

        // action
        var customerModel = new CustomerModel(createCustomerDomainWithoutId());

        // validation
        assertNull(customerModel.getId());
        assertEquals(expectedCustomerModel.getName(), customerModel.getName());
        assertEquals(expectedCustomerModel.getDocument(), customerModel.getDocument());

    }

    private Customer createCustomerDomainWithoutId() {
        return Customer
                .builder()
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    @Test
    @DisplayName("Should to generate Customer Model with Constructor Default")
    void shouldToGenerateCustomerDomainWithConstructorDefaultTest() {

        // cenary
        var customerModel = new CustomerModel();

        // validation
        assertNull(customerModel.getId());
        assertNull(customerModel.getName());
        assertNull(customerModel.getDocument());

    }

    @Test
    @DisplayName("Should to generate Customer Domain by Perfect Customer Model")
    void shouldToGenerateCustomerDomainByModelTest() {

        // cenary
        var expectedCustomerDomain = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_DOCUMENT);

        // action
        var customerDomainGenerated = createCustomerModel().toDomain();

        // validation
        assertEquals(expectedCustomerDomain.getId(), customerDomainGenerated.getId());
        assertEquals(expectedCustomerDomain.getName(), customerDomainGenerated.getName());
        assertEquals(expectedCustomerDomain.getDocument(), customerDomainGenerated.getDocument());

    }

    private CustomerModel createCustomerModel() {
        return new CustomerModel(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }

}