package com.batis.application.entity.ugc;

import com.batis.application.entity.BaseEntity;
import com.batis.application.entity.base.DeviceInfo;
import com.batis.application.entity.management.User;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Document(indexName = "test-post")
public class Post extends BaseEntity {

    @NotNull
    @ManyToOne
    private User author;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private DeviceInfo deviceInfo;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Content content;

    @ManyToOne
    private Post parent;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }
}
