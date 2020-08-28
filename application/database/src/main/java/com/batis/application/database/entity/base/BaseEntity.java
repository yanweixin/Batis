package com.batis.application.database.entity.base;

import com.batis.application.database.entity.management.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@MappedSuperclass
@JsonIgnoreProperties({"createdBy", "createdAt", "updatedBy", "updatedAt", "objectVersionNumber"})
public abstract class BaseEntity extends IdEntity implements Serializable {
    @NotNull
    @Column(updatable = false)
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(updatable = false)
    private Date createdAt;

    @NotNull
    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @NotNull
    private Date updatedAt;

    @Version
    @NotNull
    private Long objectVersionNumber;

    private Date getNow() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return new Date();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PrePersist
    public void preparePersist() {
        if (Stream.of(createdBy, createdAt, updatedBy, updatedAt).anyMatch(Objects::isNull)) {
            this.createdBy = getCurrentUser().getId();
            this.updatedBy = getCurrentUser().getId();
            this.createdAt = getNow();
            this.updatedAt = getNow();
            this.objectVersionNumber = 1L;
        }
    }

    @PreUpdate
    public void prepareUpdate() {
        this.updatedBy = getCurrentUser().getId();
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