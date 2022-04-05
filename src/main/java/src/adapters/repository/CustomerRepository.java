package src.adapters.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.adapters.repository.jpa.CustomerJpaRepository;
import src.adapters.repository.model.CustomerJpa;
import src.core.domain.exception.CustomerNotFoundException;
import src.core.domain.models.Customer;
import src.core.port.out.GetCustomerByIdOutputPort;
import src.core.port.out.SaveCustomerOutputPort;

import javax.transaction.Transactional;

@Repository
public class CustomerRepository implements SaveCustomerOutputPort, GetCustomerByIdOutputPort {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) {

        var customerJpa = new CustomerJpa(customer);

        var customerJpaSaved = customerJpaRepository.save(customerJpa);

        return customerJpaSaved.toDomain();

    }

    @Override
    public Customer getCustomerById(Long customerId) {

        var customerJpaSaved = customerJpaRepository
                .findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        return customerJpaSaved.toDomain();

    }
}
