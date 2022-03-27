package src.core.port.out;

import src.core.domain.models.Customer;

public interface SaveCustomerOutputPort {

    Customer save(Customer customer);

}
