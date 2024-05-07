package com.banking.demo.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class GoodOrder implements Serializable {

    @EmbeddedId
    private GoodOrderKey id;
    private Integer numGoods;
    private Integer cost;
    private Integer extraGoods;

    @ManyToOne
    @JoinColumn(name="id")
    private Order order;

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

    public Integer getExtraGoods() {
        return extraGoods;
    }

    public void setExtraGoods(Integer extraGoods) {
        this.extraGoods = extraGoods;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodOrder goodOrder = (GoodOrder) o;
        return Objects.equals(id, goodOrder.id) && Objects.equals(numGoods, goodOrder.numGoods) && Objects.equals(cost, goodOrder.cost) && Objects.equals(extraGoods, goodOrder.extraGoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numGoods, cost, extraGoods);
    }
}
