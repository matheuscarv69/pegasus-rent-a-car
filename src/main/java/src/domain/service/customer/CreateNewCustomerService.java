package src.domain.service.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.entity.Customer;
import src.domain.repository.CustomerRepository;
import src.domain.usercase.customer.CreateNewCustomerUseCase;

@Service
public class CreateNewCustomerService implements CreateNewCustomerUseCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("Creating Customer");

        return customerRepository.create(customer);

    }

}
