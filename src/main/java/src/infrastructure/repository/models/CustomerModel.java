package src.infrastructure.repository.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import src.domain.entity.Customer;

import javax.persistence.*;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 11)
    private String document;

    public CustomerModel(Customer customer) {

        if (Objects.nonNull(customer.getId()))
            this.id = customer.getId();

        this.name = customer.getName();
        this.document = customer.getDocument();
    }

    public Customer toDomain() {
        return Customer
                .builder()
                .id(this.id)
                .name(this.name)
                .document(this.document)
                .build();
    }

}
