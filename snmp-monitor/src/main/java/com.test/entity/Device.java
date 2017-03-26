package com.test.entity;

import com.test.snmp.SnmpDevice;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Device {

    private final String vendor;
    private final InetAddress address;
    private final SnmpDevice snmpDevice;
    private String name;


    public Device(String vendor, String address, String name, SnmpDevice snmpDevice) {
        this.vendor = vendor;
        this.address = getAddress(address);
        this.name = name;
        this.snmpDevice = snmpDevice;
    }

    private InetAddress getAddress(String address)  {
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Wrong host " + address);
        }

    }

    public SnmpDevice getSnmpDevice() {
        return snmpDevice;
    }

    public String getVendor() {
        return vendor;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", address=" + address +
                ", snmpDevice=" + snmpDevice.toString() +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (name != null ? !name.equals(device.name) : device.name != null) return false;
        if (vendor != null ? !vendor.equals(device.vendor) : device.vendor != null) return false;
        if (address != null ? !address.equals(device.address) : device.address != null) return false;
        return snmpDevice != null ? snmpDevice.equals(device.snmpDevice) : device.snmpDevice == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (vendor != null ? vendor.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (snmpDevice != null ? snmpDevice.hashCode() : 0);
        return result;
    }


}
