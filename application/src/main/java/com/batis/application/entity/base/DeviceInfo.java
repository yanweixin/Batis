package com.batis.application.entity.base;

import com.batis.application.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
public class DeviceInfo extends BaseEntity {

    private String deviceCode;

    private String deviceName;

    private String fingerPrint;

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
