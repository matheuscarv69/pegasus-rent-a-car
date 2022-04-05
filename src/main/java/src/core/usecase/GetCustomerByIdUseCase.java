package src.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.core.domain.models.Customer;
import src.core.port.in.GetCustomerByIdInputPort;
import src.core.port.out.GetCustomerByIdOutputPort;

public class GetCustomerByIdUseCase implements GetCustomerByIdInputPort {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private GetCustomerByIdOutputPort getCustomerByIdOutputPort;

    public GetCustomerByIdUseCase(GetCustomerByIdOutputPort getCustomerByIdOutputPort) {
        this.getCustomerByIdOutputPort = getCustomerByIdOutputPort;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        logger.info("Getting Customer by ID: {}", customerId);

        return getCustomerByIdOutputPort.getCustomerById(customerId);
    }
}
