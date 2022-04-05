package src.core.domain.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerResponseTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_NAME = "Matheus Carvalho";
    private static final String CUSTOMER_DOCUMENT = "42427205052";

    @Test
    @DisplayName("Should to create a Customer Response by Customer Domain")
    void shouldToCreateACustomerResponseByDomain() {

        // cenary
        var customerDomain = createCustomerDomain();

        // action
        var customerResponse = new CustomerResponse(customerDomain);

        // validation
        assertEquals(customerDomain.getId(), customerResponse.getId());
        assertEquals(customerDomain.getName(), customerResponse.getName());
        assertEquals(customerDomain.getDocument(), customerResponse.getDocument());

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