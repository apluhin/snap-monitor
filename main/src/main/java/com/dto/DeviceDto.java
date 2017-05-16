package com.dto;

import com.entity.Device;

import java.math.BigInteger;

public class DeviceDto {

    private final Long id;
    private final String name;
    private final String vendor;
    private final String ipAddress;
    private final BigInteger freeMem;
    private final boolean online;


    public DeviceDto(Long id, String name, String vendor, String ipAddress, BigInteger freeMem, boolean online) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.ipAddress = ipAddress;
        this.freeMem = freeMem;

        this.online = online;
    }

    public DeviceDto(Long id, Device device, BigInteger freeMem, boolean online) {
        this.id = id;
        this.name = device.getName();
        this.vendor = device.getVendor();
        this.ipAddress = device.getAddress().toString();
        this.freeMem = freeMem;
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

    public BigInteger getFreeMem() {
        return freeMem;
    }


    public Long getId() {
        return id;
    }
}
