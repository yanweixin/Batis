package com.batis.application.database.entity.base;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StringId {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
