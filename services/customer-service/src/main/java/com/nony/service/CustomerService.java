package com.nony.service;

import com.nony.exception.CustomerNotFoundException;
import com.nony.mapper.CustomerMapper;
import com.nony.model.Customer;
import com.nony.model.CustomerRequest;
import com.nony.model.CustomerResponse;
import com.nony.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerMapper.toCustomer(customerRequest));
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        // Fetch the customer from the database using the provided ID.
        // If the customer does not exist, throw a CustomerNotFoundException.
        var customer = this.customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", customerRequest.id())
                ));
        // Merge the updated fields from the customerRequest into the existing customer object.
        mergeCustomer(customer, customerRequest);
        // Save the updated customer back to the database.
        this.customerRepository.save(customer);
    }

    // Private method to merge updated fields from a CustomerRequest into an existing
    // Customer object.
    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        // If the firstName field in the customerRequest is not blank, update the
        // firstName field in the customer object.
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }

        // If the email field in the customerRequest is not blank, update the email field
        // in the customer object.
        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }

        // If the address field in the customerRequest is not null, update the address
        // field in the customer object.
        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    // Public method to retrieve all customers from the database and map them to a list
    // of CustomerResponse objects.
    public List<CustomerResponse> findAllCustomers() {
        // Fetch all customers from the database using the customerRepository.
        // Convert each Customer object to a CustomerResponse object using the customerMapper.
        // Collect the resulting stream of CustomerResponse objects into a list and return it.
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    // Public method to retrieve a single customer by their ID from the database and map it
    // to a CustomerResponse object.
    public CustomerResponse findById(Long id) {
        // Fetch the customer from the database using the customerRepository.
        // If the customer is found, map it to a CustomerResponse object using the
        // customerMapper.
        // If the customer is not found, throw a CustomerNotFoundException.
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    // Public method to delete a customer from the database by their ID.
    // If the customer is not found, throw a CustomerNotFoundException.
    public void deleteById(Long id) {
        // Delete the customer from the database using the customerRepository.
        this.customerRepository.deleteById(id);

        // Check if the customer is still in the database.
        // If the customer is not found, throw a CustomerNotFoundException.
        if (!this.customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id));
        }
    }
}