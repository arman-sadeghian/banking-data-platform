package com.sadeghian.banking.infrastructure.adapter.stub;

import com.sadeghian.banking.application.port.out.CustomerLookupPort;
import com.sadeghian.banking.domain.model.customer.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class StubCustomerLookupAdapter implements CustomerLookupPort {

    @Override
    public Customer findByCustomerId(String customerId) {
        return null;
    }
}
