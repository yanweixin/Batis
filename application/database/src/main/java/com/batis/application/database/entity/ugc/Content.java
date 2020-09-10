package com.batis.application.database.entity.ugc;

import com.batis.application.database.entity.base.BaseEntity;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Document(indexName = "ugc-content")
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
