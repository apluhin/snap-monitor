package com.test.dto;

import com.test.entity.Device;

public class DeviceDto {

    private final String name;
    private final String vendor;
    private final String ipAddress;
    private final boolean online;


    public DeviceDto(String name, String vendor, String ipAddress, boolean online) {
        this.name = name;
        this.vendor = vendor;
        this.ipAddress = ipAddress;

        this.online = online;
    }

    public DeviceDto(Device device, boolean online) {
        this.name = device.getName();
        this.vendor = device.getVendor();
        this.ipAddress = device.getAddress().toString();
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
}
