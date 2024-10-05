package com.ensak.positions_app.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Test
    public void shouldReturnAllCustomers(){
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("yassir@outlook.com").build();
        Customer customerTwo = Customer.builder().id(2).email("hafid@outlook.com").build();
        customerRepository.saveAll(List.of(customerOne,customerTwo));

        // Act
        List<Customer> allCustomers = customerRepository.findAll();

        // Assert
        assertEquals(2,allCustomers.size());
    }
    @Test
    public void shouldReturnCustomerByEmail(){
        // Arrange
        Customer customerOne = Customer.builder().id(3).email("yassir@outlook.com").build();
        Customer customerTwo = Customer.builder().id(4).email("hafid@outlook.com").build();
        customerRepository.saveAll(List.of(customerOne,customerTwo));

        // Act
        Customer customer = customerRepository.findByEmail("yassir@outlook.com");

        // Assert
        assertEquals(customerOne.getId(),customer.getId());
        assertEquals(customerOne.getEmail(),customer.getEmail());
    }


}