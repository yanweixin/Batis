package com.batis.application.database.entity.audit;

import com.batis.application.database.entity.base.NoSqlEntity;

public abstract class BaseAudit extends NoSqlEntity {
    private String operation;

    //id of the table being audited
    private Long lineId;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
}
