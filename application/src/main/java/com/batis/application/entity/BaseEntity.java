package com.batis.application.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    private Long id;

    @NotNull
    @CreatedBy
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @CreatedDate
    private Date createdAt;

    @NotNull
    @LastModifiedBy
    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @LastModifiedDate
    private Date updatedAt;

    @Version
    @NotNull
    private Long objectVersionNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    public void preparePersist() {
        if (Objects.isNull(this.createdAt)) {
            this.createdAt = new Date();
            this.updatedAt = new Date();
            this.objectVersionNumber = 1L;
        }
    }

    @PreUpdate
    public void prepareUpdate() {
        this.updatedAt = new Date();
        this.objectVersionNumber += 1;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }
}