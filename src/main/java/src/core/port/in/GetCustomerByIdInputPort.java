package src.core.port.in;

import src.core.domain.models.Customer;

public interface GetCustomerByIdInputPort {

    Customer getCustomerById(Long customerId);

}
