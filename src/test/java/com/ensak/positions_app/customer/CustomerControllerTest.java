package com.ensak.positions_app.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CustomerService customerService;
    @Test
    void shouldReturnListOfCustomers() throws Exception {
        // Arrange
        CustomerDto customerDtoOne = new CustomerDto(1,"yassir@outlook.com");
        CustomerDto customerDtoTwo = new CustomerDto(2,"hafid@outlook.com");
        when(customerService.search()).thenReturn(List.of(customerDtoOne,customerDtoTwo));
        // Act & Assert
        this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) content().string(containsString("yassir")));
    }

    @Test
    void read() {
    }
}