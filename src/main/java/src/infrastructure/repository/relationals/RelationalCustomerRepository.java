package src.infrastructure.repository.relationals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.domain.entity.Customer;
import src.domain.repository.CustomerRepository;
import src.infrastructure.repository.models.CustomerModel;

@Repository
public class RelationalCustomerRepository implements CustomerRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepositoryJPA customerRepositoryJPA;

    @Override
    public Customer create(Customer customer) {
        logger.info("Saving Customer");

        var savedCustomer = customerRepositoryJPA
                .save(new CustomerModel(customer))
                .toDomain();

        logger.info("Saved Customer - id: {}", savedCustomer.getId());

        return savedCustomer;

    }
}
