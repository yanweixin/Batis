package com.batis.application.database.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@JsonIgnoreProperties({"createdBy", "createdAt", "updatedBy", "updatedAt", "objectVersionNumber"})
public abstract class BaseEntity extends IdEntity implements Serializable {
    @NotNull
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @NotNull
    @LastModifiedBy
    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @NotNull
    @LastModifiedDate
    private Date updatedAt;

    @Version
    @NotNull
    private Long objectVersionNumber;

    private Date getNow() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return new Date();
    }

    @PrePersist
    public void preparePersist() {
        if (Objects.isNull(this.createdAt)) {
            this.createdBy = -1L;
            this.updatedBy = -1L;
            this.createdAt = getNow();
            this.updatedAt = getNow();
            this.objectVersionNumber = 1L;
        }
    }

    @PreUpdate
    public void prepareUpdate() {
        this.updatedBy = -1L;
        this.updatedAt = getNow();
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