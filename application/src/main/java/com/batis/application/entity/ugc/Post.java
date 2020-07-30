package com.batis.application.entity.ugc;

import com.batis.application.entity.BaseEntity;
import com.batis.application.entity.base.DeviceInfo;
import com.batis.application.entity.management.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Post extends BaseEntity {

    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    private User author;

    @NotNull
    @ManyToOne
    private DeviceInfo deviceInfo;

    @NotNull
    @OneToOne
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
