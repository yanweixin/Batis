package com.batis.application.database.entity.system;

import com.batis.application.database.entity.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class BlockList extends BaseEntity {
    private String type;

    private String value;

    private boolean pattern;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPattern() {
        return pattern;
    }

    public void setPattern(boolean pattern) {
        this.pattern = pattern;
    }
}
