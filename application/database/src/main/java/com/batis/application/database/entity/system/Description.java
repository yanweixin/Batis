package com.batis.application.database.entity.system;

import com.batis.application.database.entity.base.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Description extends IdEntity {
    @ManyToOne
    private Language language;
    private String value;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
