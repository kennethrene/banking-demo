package com.banking.demo.config;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.Offer;
import com.banking.demo.entity.OfferType;
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

    @Override
    public void run(String... args) throws Exception {
        Good apple = new Good("Apple", 60, 100);
        Good orange = new Good("Orange", 25, 50);

        List<Good> goods = new ArrayList<>();
        goods.add(apple);
        goods.add(orange);
        goods.forEach(g -> goodRepository.save(g));

        List<Good> goodOffer1 = new ArrayList<>();
        goodOffer1.add(apple);

        List<Good> goodOffer2 = new ArrayList<>();
        goodOffer2.add(orange);

        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer(1, 1,goodOffer1, OfferType.AMOUNT));
        offers.add(new Offer(3, 2, goodOffer2, OfferType.PRICE));

        offers.forEach(o -> offerRepository.save(o));
    }
}
