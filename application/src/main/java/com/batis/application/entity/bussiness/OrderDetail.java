package com.batis.application.entity.bussiness;

import com.batis.application.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Integer lineNumber;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
}
