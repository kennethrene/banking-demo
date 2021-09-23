package com.banking.demo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GoodOrderKey implements Serializable {
    private Integer idOrder;
    private Long idGood;

    public GoodOrderKey() {
    }

    public GoodOrderKey(Integer idOrder, Long idGood) {
        this.idOrder = idOrder;
        this.idGood = idGood;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdGood() {
        return idGood;
    }

    public void setIdGood(Long idGood) {
        this.idGood = idGood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodOrderKey that = (GoodOrderKey) o;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idGood, that.idGood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idGood);
    }

    @Override
    public String toString() {
        return "GoodOrderKey{" +
                "idOrder=" + idOrder +
                ", idGood=" + idGood +
                '}';
    }
}
