package com.banking.demo.controller;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.Offer;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.service.GoodService;
import com.banking.demo.service.OfferService;
import com.banking.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class MainController {
    private final OrderService orderService;
    private final OfferService offerService;
    private final GoodService goodService;

    public MainController(OrderService orderService, OfferService offerService, GoodService goodService) {
        this.orderService = orderService;
        this.offerService = offerService;
        this.goodService = goodService;
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody List<OrderBody> goods) throws NoOrderCreatedException {
        Map<Long, Integer> order = new HashMap<>();
        goods.forEach(g -> order.put(g.idGood, g.value));

        return orderService.createOrder(order);
    }

    @GetMapping(("/offers"))
    public List<Offer> getOffers() {
        return offerService.getOffers();
    }

    @GetMapping(("/goods"))
    public ResponseEntity<List<Good>> getGoods() {
        return ResponseEntity.ok(goodService.getGoods());
    }

    static class OrderBody implements Serializable {
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
