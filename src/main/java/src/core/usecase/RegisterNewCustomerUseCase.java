package src.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.core.domain.models.Customer;
import src.core.domain.models.NewCustomerRequest;
import src.core.port.in.RegisterNewCustomerInputPort;
import src.core.port.out.SaveCustomerOutputPort;

public class RegisterNewCustomerUseCase implements RegisterNewCustomerInputPort {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SaveCustomerOutputPort saveCustomerOutputPort;

    public RegisterNewCustomerUseCase(SaveCustomerOutputPort saveCustomerOutputPort) {
        this.saveCustomerOutputPort = saveCustomerOutputPort;
    }

    @Override
    public Customer registerNewCustomer(NewCustomerRequest newCustomerRequest) {
        logger.info("Creating Customer");

        var customer = newCustomerRequest.toDomain();

        return saveCustomerOutputPort.save(customer);

    }


}
