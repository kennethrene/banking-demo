package com.banking.demo.config;

import com.banking.demo.entity.Good;
import com.banking.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDBStartupRunner implements CommandLineRunner {
    @Autowired
    GoodRepository goodRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Good> goods = new ArrayList<>();
        goods.add(new Good("Apple", 60, 100));
        goods.add(new Good("Orange", 25, 50));

        goods.forEach(g -> goodRepository.save(g));
    }
}
