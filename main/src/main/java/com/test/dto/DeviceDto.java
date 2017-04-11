package com.test.dto;

import com.test.entity.Device;

import java.math.BigInteger;

public class DeviceDto {

    private final String name;
    private final String vendor;
    private final String ipAddress;
    private final BigInteger currentRam;
    private final boolean online;


    public DeviceDto(String name, String vendor, String ipAddress, BigInteger currentRam, boolean online) {
        this.name = name;
        this.vendor = vendor;
        this.ipAddress = ipAddress;
        this.currentRam = currentRam;

        this.online = online;
    }

    public DeviceDto(Device device, BigInteger currentRam, boolean online) {
        this.name = device.getName();
        this.vendor = device.getVendor();
        this.ipAddress = device.getAddress().toString();
        this.currentRam = currentRam;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isOnline() {
        return online;
    }

    public BigInteger getCurrentRam() {
        return currentRam;
    }
}
