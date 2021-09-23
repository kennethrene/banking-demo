package com.banking.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate date;

    @OneToMany
    private List<GoodOrder> goodOrders;

    public Order() {
    }

    public Order(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

   public List<GoodOrder> getGoodOrders() {
        return goodOrders;
    }

    public void setGoodOrders(List<GoodOrder> goodOrders) {
        this.goodOrders = goodOrders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
