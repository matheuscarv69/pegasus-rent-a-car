package src.application.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import src.domain.entity.Customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class NewCustomerRequest {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String document;

    public Customer toDomain() {
        return Customer
                .builder()
                .name(this.name)
                .document(this.document)
                .build();
    }

}
