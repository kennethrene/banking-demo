package com.banking.demo.service;

import com.banking.demo.entity.GoodOrder;
import com.banking.demo.repository.GoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodOrderService {
    @Autowired
    GoodOrderRepository goodOrderRepository;

    public GoodOrder save(GoodOrder goodOrder) {
        return goodOrderRepository.save(goodOrder);
    }
}
