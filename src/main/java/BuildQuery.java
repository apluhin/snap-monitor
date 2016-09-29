import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import snmp.SnmpDevice;

import java.io.IOException;

/**
 * Created by alexey on 29.09.16.
 */
public class BuildQuery {
    private final Device device;



    public BuildQuery(Device device) {
        this.device = device;
    }




    public Snmp buildQuery() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        if(device.getSnmpDevice().getVersion() == SnmpConstants.version3) {setUsm(snmp);}
        transport.listen();
        return snmp;
    }

    private void setUsm(Snmp snmp) {

        USM usm = new USM(SecurityProtocols.getInstance(),
                new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        SnmpDevice s = device.getSnmpDevice();
        snmp.getUSM().addUser(
                new OctetString(s.getUsername()),
                new UsmUser(new OctetString(
                        s.getUsername()),
                        s.getTypeEncript().getMethodEncrypt(),
                        new OctetString(s.getHashPassword()),
                        s.getTypeEncript().getMethodEncrypt(),
                        new OctetString(s.getEncryptionPassword())));

    }
}
