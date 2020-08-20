package com.batis.application.database.entity.physics;

import com.batis.application.database.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DeviceInfo extends BaseEntity {

    private String deviceCode;

    private String deviceName;

    @Column(unique = true)
    private String fingerPrint;

    @Column(unique = true)
    private String mac;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
