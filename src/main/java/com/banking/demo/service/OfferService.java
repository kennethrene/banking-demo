package com.banking.demo.service;

import com.banking.demo.entity.Good;
import com.banking.demo.entity.GoodOrder;
import com.banking.demo.entity.Offer;
import com.banking.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public boolean isOfferAvailable() {
        return true;
    }

    public Optional<Offer> isGoodInOffer(Good good) {
        return offerRepository.findAll().stream().filter((Offer o) -> o.getGoods().stream().anyMatch((Good g) -> g.getId() == good.getId())).findFirst();
    }

    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    public GoodOrder updateOrderWithOffer(Good good, GoodOrder goodOrder, Integer numGoodRequest) {
        if (isOfferAvailable()) {
            Optional<Offer> offer = isGoodInOffer(good);

            if (offer.isPresent()) {
                switch (offer.get().getOfferType()) {
                    case AMOUNT: {
                        goodOrder.setExtraGoods(offer.get().getFinalItems() * numGoodRequest / offer.get().getBaseItems());
                        break;
                    }
                    case PRICE: {
                        Integer items = numGoodRequest / offer.get().getBaseItems();
                        offer.get().setFinalItems(numGoodRequest - items);

                        goodOrder.setCost(offer.get().getFinalItems() * good.getCost());
                    }
                }
            }
        }

        return goodOrder;
    }
}
