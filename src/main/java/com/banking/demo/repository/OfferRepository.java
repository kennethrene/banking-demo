package com.banking.demo.repository;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer>  {
    @Query(value = "SELECT o.goods FROM Offer o WHERE o.id = :id")
    List<Good> findGoodsByOffer(@Param("id") Integer id);
}
