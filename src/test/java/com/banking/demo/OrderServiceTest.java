package com.banking.demo;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.exception.NoStockAvailableException;
import com.banking.demo.repository.GoodRepository;
import com.banking.demo.service.CustomerService;
import com.banking.demo.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderServiceTest {
	@Autowired
	private OrderService orderService;

	@Autowired
	private GoodRepository goodRepository;

	@Autowired
	private CustomerService customerService;

	@BeforeAll
	static void setup() {
	}

	@Test
	void createOrderWithoutOfferOk() {
		Customer customer = customerService.getCustomer(1);
		Order newOrder = new Order(LocalDate.now(), customer);
		newOrder.setId(1);

		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(2L, 2);

		try {
			Order order = orderService.createOrder(goodsOrder,customer.getId());

			assertNotNull(order);
			assertEquals(order.getGoodOrders().size(), 1);
			assertEquals(goodRepository.findById(2L).getStock(), 48);
			assertEquals(order.getGoodOrders().get(0).getCost(), 50);
		} catch (NoOrderCreatedException e) {
		}
	}

	@Test
	void createOrderNoStockAvailable() {
		Customer customer = customerService.getCustomer(1);

		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(1L, 200);
		goodsOrder.put(2L, 10);

		try {
			orderService.createOrder(goodsOrder, customer.getId());
			assert false;
		} catch (NoStockAvailableException | NoOrderCreatedException e) {
			assert true;
		}
	}

	@Test
	void createOrderWithOffer() {
		Customer customer = customerService.getCustomer(1);
		Order newOrder = new Order(LocalDate.now(), customer);
		newOrder.setId(1);

		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(1L, 20);
		goodsOrder.put(2L, 10);

		try {
			Order order = orderService.createOrder(goodsOrder, customer.getId());

			assertNotNull(order);
			assertEquals(order.getGoodOrders().size(), 2);
			assertEquals(goodRepository.findById(1L).getStock(), 80);
			assertEquals(goodRepository.findById(2L).getStock(), 38);
			assertEquals(order.getGoodOrders().get(0).getCost(), 1200);

			assertEquals(order.getGoodOrders().get(0).getNumGoods(), 20);
			assertEquals(order.getGoodOrders().get(0).getExtraGoods(), 20);

			assertEquals(order.getGoodOrders().get(1).getNumGoods(), 10);
			assertEquals(order.getGoodOrders().get(1).getCost(), 175);
		} catch (NoOrderCreatedException e) {
		}
	}

	@Test
	void createOrdersCustomer() {
		Customer customer = customerService.getCustomer(2);
		Order newOrder = new Order(LocalDate.now(), customer);
		newOrder.setId(1);

		Map<Long, Integer> goodsOrder1 = new HashMap<>();
		goodsOrder1.put(1L, 20);
		goodsOrder1.put(2L, 10);

		Map<Long, Integer> goodsOrder2 = new HashMap<>();
		goodsOrder2.put(1L, 5);
		goodsOrder2.put(2L, 6);

		Map<Long, Integer> goodsOrder3 = new HashMap<>();
		goodsOrder3.put(2L, 2);

		List<Map> orders = List.of(goodsOrder1, goodsOrder2, goodsOrder3);

		try {
			orders.forEach(o -> orderService.createOrder(o, customer.getId()));

			List<Order> customerOrders = customerService.getAllOrders(customer);

			assertNotNull(customerOrders);
			assertEquals(customerOrders.size(), 3);
		} catch (NoOrderCreatedException e) {
		}
	}
}
