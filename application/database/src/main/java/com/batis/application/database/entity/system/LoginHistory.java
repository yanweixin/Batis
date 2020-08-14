package com.batis.application.database.entity.system;

import com.batis.application.database.entity.BaseEntity;
import com.batis.application.database.entity.base.DeviceInfo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class LoginHistory extends BaseEntity {
    @NotNull
    private Long userId;

    private String ip;

    @ManyToOne
    private DeviceInfo deviceInfo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
