package src.core.port.in;

import src.core.domain.models.Customer;
import src.core.domain.models.NewCustomerRequest;

public interface RegisterNewCustomerInputPort {

    Customer registerNewCustomer(NewCustomerRequest newCustomerRequest);

}
