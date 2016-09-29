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
}
