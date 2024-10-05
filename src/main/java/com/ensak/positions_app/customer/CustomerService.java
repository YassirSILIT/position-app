package com.ensak.positions_app.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    List<CustomerDto> search(){
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDto(customer.getId(), customer.getEmail()))
                .collect(Collectors.toList());
    }

    public CustomerDto read(int id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No customer for id " +id));
        return new CustomerDto(customer.getId(), customer.getEmail());
    }
}
