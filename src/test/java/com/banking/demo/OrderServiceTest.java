package com.banking.demo;

import com.banking.demo.entity.Order;
import com.banking.demo.exception.NoOrderCreatedException;
import com.banking.demo.exception.NoStockAvailableException;
import com.banking.demo.repository.GoodRepository;
import com.banking.demo.service.GoodService;
import com.banking.demo.service.OfferService;
import com.banking.demo.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
	private OfferService offerService;

	@Autowired
	private GoodRepository goodRepository;

	@BeforeAll
	static void setup() {
	}

	@Test
	void createOrderWithoutOfferOk() {
		Order newOrder = new Order(LocalDate.now());
		newOrder.setId(1);

		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(2L, 2);

		try {
			Order order = orderService.createOrder(goodsOrder);

			assertNotNull(order);
			assertEquals(order.getGoodOrders().size(), 1);
			assertEquals(goodRepository.findById(2L).getStock(), 48);
			assertEquals(order.getGoodOrders().get(0).getCost(), 50);
		} catch (NoOrderCreatedException e) {
		}
	}

	@Test
	void createOrderNoStockAvailable() {
		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(1L, 200);
		goodsOrder.put(2L, 10);

		try {
			orderService.createOrder(goodsOrder);
			assert false;
		} catch (NoStockAvailableException | NoOrderCreatedException e) {
			assert true;
		}
	}

	@Test
	void createOrderWithOffer() {
		Order newOrder = new Order(LocalDate.now());
		newOrder.setId(1);

		Map<Long, Integer> goodsOrder = new HashMap<>();
		goodsOrder.put(1L, 20);
		goodsOrder.put(2L, 10);

		try {
			Order order = orderService.createOrder(goodsOrder);

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
}
