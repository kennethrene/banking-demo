package com.banking.demo.entity;

import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
