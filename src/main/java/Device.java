public class Device {

    private final String vendor;
    private final String address;
    private final Snmp snmp;

    public Device(String vendor, String address, Snmp snmp) {
        this.vendor = vendor;
        this.address = address;
        this.snmp = snmp;
    }

    public Snmp getSnmp() {
        return snmp;
    }
}
