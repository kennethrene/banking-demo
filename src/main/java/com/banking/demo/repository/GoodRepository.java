package com.banking.demo.repository;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Integer> {
    int countByIdAndStockIsGreaterThanEqual(Long id, Integer value);

    Good findById(Long idGood);
}
