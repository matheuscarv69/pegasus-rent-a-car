package src.infrastructure.relationals;

import org.springframework.data.jpa.repository.JpaRepository;
import src.infrastructure.models.CustomerModel;

public interface CustomerRepositoryJPA extends JpaRepository<CustomerModel, Long> {
}
