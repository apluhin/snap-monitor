package com.test.dto;

import com.test.entity.Device;

import java.util.Map;

public class DeviceDto {

    private final String name;
    private final String vendor;
    private final String ipAddress;
    private Map<String, String> snmpDevice;

    public DeviceDto(String name, String vendor, String ipAddress, Map<String, String> snmpDevice) {
        this.name = name;
        this.vendor = vendor;
        this.ipAddress = ipAddress;
        this.snmpDevice = snmpDevice;
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
