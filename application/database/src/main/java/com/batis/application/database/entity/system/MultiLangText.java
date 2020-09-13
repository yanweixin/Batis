package com.batis.application.database.entity.system;

import com.batis.application.database.entity.base.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MultiLangText extends IdEntity {
    private Integer textId;
    @ManyToOne
    private Language language;
    private String text;

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
