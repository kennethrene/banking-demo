package com.banking.demo.repository;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
