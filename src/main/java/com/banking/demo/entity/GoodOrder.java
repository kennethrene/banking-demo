package com.banking.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class GoodOrder implements Serializable {

    @EmbeddedId
    private GoodOrderKey id;
    private Integer numGoods;
    private Integer cost;

    public GoodOrder() {
    }

    public GoodOrder(Good good, Order order, Integer numGoods) {
        this.numGoods = numGoods;
        this.cost = numGoods * good.getCost();
        this.id = new GoodOrderKey(order.getId(), good.getId());
    }

    public GoodOrderKey getId() {
        return id;
    }

    public void setId(GoodOrderKey id) {
        this.id = id;
    }

    public Integer getNumGoods() {
        return numGoods;
    }

    public void setNumGoods(Integer numGoods) {
        this.numGoods = numGoods;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodOrder goodOrder = (GoodOrder) o;
        return Objects.equals(id, goodOrder.id) && Objects.equals(numGoods, goodOrder.numGoods) && Objects.equals(cost, goodOrder.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numGoods, cost);
    }
}
