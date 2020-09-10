package com.batis.application.database.entity.ugc;

import com.batis.application.database.entity.base.BaseEntity;
import com.batis.application.database.entity.management.User;
import com.batis.application.database.entity.physics.DeviceInfo;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Post extends BaseEntity {

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = {"birthday", "enabled", "country"}, allowSetters = true)
    private User author;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DeviceInfo deviceInfo;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Content content;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
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
