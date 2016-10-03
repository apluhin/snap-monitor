package main;

import snmp.SnmpDevice;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Device {

    private final String vendor;
    private final InetAddress address;
    private final SnmpDevice snmpDevice;

    public Device(String vendor, String address, SnmpDevice snmpDevice) {
        this.vendor = vendor;
        this.address = getAddress(address);
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

    @Override
    public String toString() {
        return "Device{" +
                "vendor='" + vendor + '\'' +
                ", address='" + address + '\'' +
                ", snmpDevice=" + snmpDevice.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (vendor != null ? !vendor.equals(device.vendor) : device.vendor != null) return false;
        if (address != null ? !address.equals(device.address) : device.address != null) return false;
        return snmpDevice != null ? snmpDevice.equals(device.snmpDevice) : device.snmpDevice == null;

    }

    @Override
    public int hashCode() {
        int result = vendor != null ? vendor.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (snmpDevice != null ? snmpDevice.hashCode() : 0);
        return result;
    }
}
