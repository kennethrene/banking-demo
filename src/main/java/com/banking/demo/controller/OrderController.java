package com.banking.demo.controller;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.exception.NoOrderFoundException;
import com.banking.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBody createOrder(@org.springframework.web.bind.annotation.RequestBody OrderController.RequestBody orderBody) throws NoOrderCreatedException {
        Map<Long, Integer> order = new HashMap<>();
        orderBody.getGoods().forEach(g -> order.put(g.idGood, g.value));

        return new ResponseBody().build(orderService.createOrder(order, orderBody.getIdCustomer()));
    }

    @GetMapping("/{id}")
    public ResponseBody findOne(@PathVariable Integer id) {
        return new ResponseBody().build(orderService.getOrderById(id));
    }

    @GetMapping()
    public List<ResponseBody> findAll() {
        List<Order> orders = orderService.getAllOrders();
        List<ResponseBody> responseBody = new ArrayList<>();
        orders.forEach(o -> responseBody.add(new ResponseBody().build(o)));

        return responseBody;
    }

    static class RequestBody {
        Integer idCustomer;
        List<goodsBody> goods;

        public Integer getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(Integer idCustomer) {
            this.idCustomer = idCustomer;
        }

        public List<goodsBody> getGoods() {
            return goods;
        }

        public void setGoods(List<goodsBody> goods) {
            this.goods = goods;
        }
    }

    static class goodsBody {
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

    static class ResponseBody {
        Integer idOrder;
        LocalDate date;
        List<GoodOrder> goods;
        Customer customer;

        public ResponseBody build(Order order) {
            this.idOrder = order.getId();
            this.date = order.getDate();
            this.goods = order.getGoodOrders();
            this.customer = new Customer(order.getCustomer().getId(), order.getCustomer().getName());

            return this;
        }

        public Integer getIdOrder() {
            return idOrder;
        }

        public void setIdOrder(Integer idOrder) {
            this.idOrder = idOrder;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public List<GoodOrder> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodOrder> goods) {
            this.goods = goods;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
    }
}
