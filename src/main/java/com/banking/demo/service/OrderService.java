package com.banking.demo.service;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodOrderService goodOrderService;

    @Autowired
    private GoodService goodService;

    public Order createOrder(Map<Long, Integer> goods) throws NoOrderCreatedException {
        if (goodService.goodsAvailable(goods)) {
            Order order = orderRepository.save(new Order(LocalDate.now()));
            List<GoodOrder> goodOrders = new ArrayList<>();

            goods.forEach((idGood, numGoodRequest) -> {
                Good good = goodService.findGoodById(idGood);
                GoodOrder goodOrder = new GoodOrder(good, order, numGoodRequest);
                goodOrders.add(goodOrder);
                goodService.discountStock(good, numGoodRequest);
                goodOrderService.save(goodOrder);
            });

            order.setGoodOrders(goodOrders);

            return order;
        } else {
            throw new NoOrderCreatedException();
        }
    }


}