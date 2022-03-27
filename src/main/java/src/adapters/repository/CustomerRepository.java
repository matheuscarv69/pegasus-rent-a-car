package src.adapters.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.adapters.repository.jpa.CustomerJpaRepository;
import src.adapters.repository.model.CustomerJpa;
import src.core.domain.models.Customer;
import src.core.port.out.SaveCustomerOutputPort;

@Repository
public class CustomerRepository implements SaveCustomerOutputPort {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer save(Customer customer) {

        var customerJpa = new CustomerJpa(customer);

        var customerJpaSaved = customerJpaRepository.save(customerJpa);

        return customerJpaSaved.toDomain();

    }


}
