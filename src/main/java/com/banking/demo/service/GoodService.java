package com.banking.demo.service;

import com.banking.demo.entity.Good;
import com.banking.demo.exception.NoStockAvailableException;
import com.banking.demo.repository.GoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodService {
    private final GoodRepository goodRepository;

    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

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

    public List<Good> getGoods() {
        return goodRepository.findAll();
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
