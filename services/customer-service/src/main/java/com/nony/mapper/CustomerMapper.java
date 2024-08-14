package com.nony.mapper;

import com.nony.model.Customer;
import com.nony.model.CustomerRequest;
import com.nony.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    //toCustomer() maps a CustomerRequest object to a Customer object.
    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(customerRequest.address()).build();
    }

    //fromCustomer() maps a Customer object to a CustomerResponse object.
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
