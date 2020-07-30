package com.batis.application.entity.ugc;

import com.batis.application.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Content extends BaseEntity {
    private String title;

    @NotBlank
    @Column(length = 65536)
    @Size(min = 1, max = 65536)
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
