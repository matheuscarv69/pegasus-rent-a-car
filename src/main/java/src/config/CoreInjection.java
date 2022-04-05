package src.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import src.core.port.in.GetCustomerByIdInputPort;
import src.core.port.in.RegisterNewCustomerInputPort;
import src.core.port.out.GetCustomerByIdOutputPort;
import src.core.port.out.SaveCustomerOutputPort;
import src.core.usecase.GetCustomerByIdUseCase;
import src.core.usecase.RegisterNewCustomerUseCase;

@Configuration
public class CoreInjection {

    @Bean
    public RegisterNewCustomerInputPort registerNewCustomer(SaveCustomerOutputPort saveCustomerOutputPort) {

        return new RegisterNewCustomerUseCase(saveCustomerOutputPort);

    }

    @Bean
    public GetCustomerByIdInputPort getCustomerById(GetCustomerByIdOutputPort getCustomerByIdOutputPort){

        return  new GetCustomerByIdUseCase(getCustomerByIdOutputPort);

    }

}
