package com.nony.service;

import com.nony.exception.CustomerNotFoundException;
import com.nony.mapper.CustomerMapper;
import com.nony.model.Customer;
import com.nony.model.CustomerRequest;
import com.nony.model.CustomerResponse;
import com.nony.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerMapper.toCustomer(customerRequest));
    }

    public Optional<CustomerResponse> findCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::fromCustomer);
    }


    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).collect(java.util.stream.Collectors.toList());
    }


    public void updateCustomerById(Long id, CustomerRequest customerRequest) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = customerMapper.toCustomer(customerRequest);
            updatedCustomer.setFirstName(existingCustomer.get().getFirstName());
            updatedCustomer.setLastName(existingCustomer.get().getLastName());
            updatedCustomer.setEmail(existingCustomer.get().getEmail());
            updatedCustomer.setAddress(existingCustomer.get().getAddress());
            customerRepository.save(updatedCustomer);
        } else {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
    }

    public void deleteCustomerById(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
    }
}