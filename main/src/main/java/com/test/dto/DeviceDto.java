package com.test.dto;

import com.test.entity.Device;

public class DeviceDto {

    private final String name;
    private final String vendor;
    private final String ipAddress;

    public DeviceDto(String name, String vendor, String ipAddress) {
        this.name = name;
        this.vendor = vendor;
        this.ipAddress = ipAddress;
    }

    public DeviceDto(Device device) {
        this.name = device.getName();
        this.vendor = device.getVendor();
        this.ipAddress = device.getAddress().toString();
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
}
