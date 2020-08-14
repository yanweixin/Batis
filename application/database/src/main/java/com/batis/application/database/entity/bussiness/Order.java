package com.batis.application.database.entity.bussiness;

import com.batis.application.database.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Order extends BaseEntity {

    @NotNull
    private String orderNumber;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
