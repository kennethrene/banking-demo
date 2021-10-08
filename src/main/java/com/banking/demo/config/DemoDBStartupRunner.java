package com.banking.demo.config;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.Good;
import com.banking.demo.entity.Offer;
import com.banking.demo.entity.OfferType;
import com.banking.demo.repository.CustomerRepository;
import com.banking.demo.repository.GoodRepository;
import com.banking.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDBStartupRunner implements CommandLineRunner {
    @Autowired
    GoodRepository goodRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Good apple = new Good(1L, "Apple", 60, 100);
        Good orange = new Good(2L, "Orange", 25, 50);

        List<Good> goods = new ArrayList<>();
        goods.add(apple);
        goods.add(orange);
        goods.forEach(g -> goodRepository.save(g));

        List<Good> goodOffer1 = new ArrayList<>();
        goodOffer1.add(apple);

        List<Good> goodOffer2 = new ArrayList<>();
        goodOffer2.add(orange);

        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer(1, 1, 1,goodOffer1, OfferType.AMOUNT));
        offers.add(new Offer(2, 3, 2, goodOffer2, OfferType.PRICE));

        offers.forEach(o -> offerRepository.save(o));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1,"Kenneth"));
        customers.add(new Customer(2,"Donna"));

        customers.forEach(c -> customerRepository.save(c));
    }
}
