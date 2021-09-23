package com.banking.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Offer implements Serializable {
    @Id
    private Integer id;
    private OfferType offerType;
    private Integer baseItems;
    private Integer finalItems;

    @OneToMany
    private List<Good> goods;

    public Offer() {
    }

    public Offer(Integer id, Integer baseItems, Integer finalItems, List<Good> goods, OfferType offerType) {
        this.id = id;
        this.baseItems = baseItems;
        this.finalItems = finalItems;
        this.goods = goods;
        this.offerType = offerType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseItems() {
        return baseItems;
    }

    public void setBaseItems(Integer baseAmount) {
        this.baseItems = baseAmount;
    }

    public Integer getFinalItems() {
        return finalItems;
    }

    public void setFinalItems(Integer extraAmount) {
        this.finalItems = extraAmount;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id) && Objects.equals(baseItems, offer.baseItems) && Objects.equals(finalItems, offer.finalItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, baseItems, finalItems);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", baseAmount=" + baseItems +
                ", extraAmount=" + finalItems +
                ", goods=" + goods +
                '}';
    }
}
