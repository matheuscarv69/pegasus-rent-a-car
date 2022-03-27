package src.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import src.adapters.repository.model.CustomerJpa;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpa, Long> {
}
