package src.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String document;

}
