package com.banking.demo.service;

import com.banking.demo.entity.Good;
import com.banking.demo.exception.NoStockAvailableException;
import com.banking.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;

    public Good discountStock(Good good, Integer totalDiscount) {
        good.setStock(good.getStock() - totalDiscount);
        return goodRepository.save(good);
    }

    public Good findGoodById(Long idGood) {
        return goodRepository.findById(idGood);
    }

    public boolean isGoodAvailable(Long idGood, Integer required) {
        return goodRepository.countByIdAndStockIsGreaterThanEqual(idGood, required) > 0 ? true : false;
    }

    public boolean goodsAvailable(Map<Long, Integer> goods) {
        if (goods != null) {
            goods.forEach((g, v) -> {
                if (!isGoodAvailable(g, v)) {
                    throw new NoStockAvailableException();
                }
            });

            return true;
        } else {
            return false;
        }
    }
}
