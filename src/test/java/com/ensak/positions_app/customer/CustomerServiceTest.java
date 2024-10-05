package com.ensak.positions_app.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerService customerService;
    @Test
    void shouldReturnAllCustomers() {
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("yassir@outlook.com").build();
        Customer customerTwo = Customer.builder().id(2).email("hafid@outlook.com").build();
        when(customerRepository.findAll()).thenReturn(List.of(customerOne,customerTwo));

        // Act
        List<CustomerDto> allCustomers = customerService.search();

        // Assert
        assertEquals(2,allCustomers.size());
    }
    @Test
    void shouldReturnCustomerById(){
        // Arrange
        Customer customerOne = Customer.builder().id(3).email("yassir@outlook.com").build();
        when(customerRepository.findById(3)).thenReturn(Optional.of(customerOne));

        // Act
        CustomerDto customerDto = customerService.read(3);

        // Assert
        assertEquals(customerOne.getId(),customerDto.id());
    }
    @Test
    void shouldThrowException(){
        // Arrange
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        // Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.read(1)
        );
        assertEquals("No customer for id 1",exception.getMessage());
    }
}