package src.adapters.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import src.core.domain.models.Customer;
import src.core.domain.models.NewCustomerRequest;
import src.core.port.in.RegisterNewCustomerInputPort;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CreateNewCustomerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegisterNewCustomerInputPort registerNewCustomerInputPort;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody @Valid NewCustomerRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        logger.info("Receiving request for create Customer: {}", request.getName());

        var customer = registerNewCustomerInputPort.registerNewCustomer(request);

        var uri = uriBuilder
                .path("/customers/{customerId}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

}
