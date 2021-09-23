package com.banking.demo.controller;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody List<orderBody> goods) throws NoOrderCreatedException {
        Map<Long, Integer> order = new HashMap<>();
        goods.forEach(g -> order.put(g.idGood, g.value));

        return orderService.createOrder(order);
    }

    static class orderBody implements Serializable {
        Long idGood;
        Integer value;

        public Long getIdGood() {
            return idGood;
        }

        public void setIdGood(Long idGood) {
            this.idGood = idGood;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
