package com.sadeghian.banking.infrastructure.adapter.stub;

import com.sadeghian.banking.application.port.out.CustomerLookupPort;
import com.sadeghian.banking.domain.model.customer.Customer;
import com.sadeghian.banking.domain.model.customer.CustomerType;
import com.sadeghian.banking.domain.model.customer.RiskLevel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class StubCustomerLookupAdapter implements CustomerLookupPort {

    @Override
    public Customer findByCustomerId(String customerId) {
        return new Customer(
                customerId,
                CustomerType.INDIVIDUAL,
                RiskLevel.LOW
        );
    }
}