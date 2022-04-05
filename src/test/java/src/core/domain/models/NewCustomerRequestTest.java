package src.core.domain.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewCustomerRequestTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @Test
    @DisplayName("Should to convert Customer Request to Customer Domain")
    void toConvertCustomerRequestToCustomerDomainTest() {

        // cenary
        var expectedCustomer = createExpectedCustomer();

        // action
        var customerDomain = createCustomerRequest().toDomain();

        // validation
        assertNull(customerDomain.getId());
        assertEquals(expectedCustomer.getName(), customerDomain.getName());
        assertEquals(expectedCustomer.getDocument(), customerDomain.getDocument());

    }

    private Customer createExpectedCustomer() {
        return Customer
                .builder()
                .id(CUSTOMER_ID)
                .name(CUSTOMER_NAME)
                .document(CUSTOMER_DOCUMENT)
                .build();
    }

    private NewCustomerRequest createCustomerRequest() {
        return new NewCustomerRequest(CUSTOMER_NAME, CUSTOMER_DOCUMENT);
    }
}