package src.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class NewCustomerRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11, message = "tamanho deve ser de 11 caracteres")
    private String document;

    public Customer toDomain() {
        return Customer
                .builder()
                .name(this.name)
                .document(this.document)
                .build();
    }
}
