package src.adapters.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.core.domain.models.CustomerResponse;
import src.core.port.in.GetCustomerByIdInputPort;

@RestController
@RequestMapping("/customers")
public class GetCustomerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetCustomerByIdInputPort getCustomerByIdInputPort;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {

        logger.info("Receiving request for to Get Customer by Id");

        var customerDomain = getCustomerByIdInputPort.getCustomerById(customerId);

        return ResponseEntity.ok(new CustomerResponse(customerDomain));

    }


}
