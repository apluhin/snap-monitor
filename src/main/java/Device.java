import snmp.Snmp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Device {

    private final String vendor;
    private final InetAddress address;
    private final Snmp snmp;

    public Device(String vendor, String address, Snmp snmp) {
        this.vendor = vendor;
        this.address = getAdress(address);
        this.snmp = snmp;
    }

    private InetAddress getAdress(String address)  {
        try {
            return InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Wrong host " + address);
        }

    }

    public Snmp getSnmp() {
        return snmp;
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
                ", snmp=" + snmp.toString() +
                '}';
    }
}
