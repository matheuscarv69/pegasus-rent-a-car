package src.core.domain.models;

import lombok.Getter;

@Getter
public class CustomerResponse {

    private Long id;
    private String name;
    private String document;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.document = customer.getDocument();
    }
}
