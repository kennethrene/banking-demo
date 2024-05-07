package com.banking.demo.repository;

import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.GoodOrderKey;
import com.banking.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodOrderRepository extends JpaRepository<GoodOrder, GoodOrderKey> {
    GoodOrder findByOrder(Order order);
}
