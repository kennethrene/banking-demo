package com.banking.demo.repository;

import com.banking.demo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer>  {
}
