package build;

import main.Device;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.OctetString;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import snmp.GenerateUsmUser;

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
        snmp.getUSM().addUser(new GenerateUsmUser(device.getSnmpDevice()).generateUsmUser());

    }
}
