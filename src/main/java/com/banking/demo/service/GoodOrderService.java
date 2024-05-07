package com.banking.demo.service;

import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.Order;
import com.banking.demo.repository.GoodOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GoodOrderService {
    private final GoodOrderRepository goodOrderRepository;

    public GoodOrderService(GoodOrderRepository goodOrderRepository) {
        this.goodOrderRepository = goodOrderRepository;
    }

    public GoodOrder save(GoodOrder goodOrder) {
        return goodOrderRepository.save(goodOrder);
    }

    public GoodOrder getOrder(Order order) {
        return goodOrderRepository.findByOrder(order);
    }
}
