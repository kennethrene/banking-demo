package com.banking.demo.service;

import com.banking.demo.entity.*;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.exception.NoOrderFoundException;
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

    @Autowired
    private OfferService offerService;

    @Autowired
    private CustomerService customerService;

    public Order createOrder(Map<Long, Integer> goods, Integer idCustomer) throws NoOrderCreatedException {
        if (goodService.goodsAvailable(goods)) {
            Order order = orderRepository.save(new Order(LocalDate.now(), customerService.getCustomer(idCustomer)));
            List<GoodOrder> goodOrders = new ArrayList<>();

            goods.forEach((idGood, numGoodRequest) -> {
                Good good = goodService.findGoodById(idGood);

                GoodOrder goodOrder = new GoodOrder(good, order, numGoodRequest);
                goodOrders.add(goodOrder);

                goodOrder = offerService.updateOrderWithOffer(good, goodOrder, numGoodRequest);
                goodOrderService.save(goodOrder);

                goodService.discountStock(good, numGoodRequest);
            });

            order.setGoodOrders(goodOrders);
            orderRepository.save(order);

            return order;
        } else {
            throw new NoOrderCreatedException();
        }
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(NoOrderFoundException::new);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
