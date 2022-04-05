package src.core.port.out;

import src.core.domain.models.Customer;

public interface GetCustomerByIdOutputPort {

    Customer getCustomerById(Long customerId);

}
