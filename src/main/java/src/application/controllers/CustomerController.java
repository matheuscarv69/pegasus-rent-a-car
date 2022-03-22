package src.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import src.application.controllers.requests.NewCustomerRequest;
import src.domain.usercase.customer.CreateNewCustomerUseCase;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreateNewCustomerUseCase createNewCustomerUseCase;

    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody @Valid NewCustomerRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        logger.info("Receiving request for create Customer: {}", request.getName());

        var customer = createNewCustomerUseCase.createCustomer(request.toDomain());

        var uri = uriBuilder
                .path("/customers/{customerId}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}
