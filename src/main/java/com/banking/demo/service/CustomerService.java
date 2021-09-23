package com.banking.demo.service;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.CustomerNotFountException;
import com.banking.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFountException::new);
    }

    public Customer getCustomer(String name) {
        return customerRepository.findCustomerByName(name);
    }

    public List<Order> getAllOrders(Customer customer) {
        return customerRepository.getAllOrders(customer.getId());
    }
}
