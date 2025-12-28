package com.sadeghian.banking.application.port.out;

import com.sadeghian.banking.domain.model.customer.Customer;

public interface CustomerLookupPort {
    Customer findByCustomerId(String customerId);
}
