package com.banking.demo.repository;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByName(String name);

    @Query(value = "SELECT c.orders FROM Customer c WHERE c.id = :id")
    List<Order> getAllOrders(@Param("id") Integer id);
}
