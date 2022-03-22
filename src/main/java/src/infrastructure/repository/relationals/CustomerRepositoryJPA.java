package src.infrastructure.repository.relationals;

import org.springframework.data.jpa.repository.JpaRepository;
import src.infrastructure.repository.models.CustomerModel;

public interface CustomerRepositoryJPA extends JpaRepository<CustomerModel, Long> {
}
