package com.banking.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Good implements Serializable {
    @Id
    private Long id;
    private String name;
    private Integer cost;
    private Integer stock;

    @ManyToOne
    private Offer offer;

    public Good() {
    }

    public Good(Long id, String name, Integer cost, Integer stock) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return Objects.equals(id, good.id) && Objects.equals(name, good.name) && Objects.equals(cost, good.cost) && Objects.equals(stock, good.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, stock);
    }
}
