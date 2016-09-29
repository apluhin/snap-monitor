import org.snmp4j.*;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class SNMPManager {
    private static String ipAddress = "192.168.0.2";

    private static String port = "161";
    //snmp-server   user   operator  netadmins  v3  auth  md5 AuthPassw0rd priv des56 EncryptionPassw0rd
    // OID of MIB RFC 1213; Scalar Object = .iso.org.dod.internet.mgmt.mib-2.system.sysDescr.0
    private static String oidValue = ".1.3.6.1.4.1.9.2.1.56.0";  // ends with 0 for scalar object

    private static int snmpVersion = SnmpConstants.version3;

    private static String community = "test_g";

    public static void main(String[] args) throws Exception {
        // add user to the USM




    }


    public ResponseEvent sendRequest(String ipAddress, SnmpConstants version, SecurityLevel securityLevel, UsmUser usmUser) throws IOException {
        Address targetAddress = GenericAddress.parse("udp:192.168.0.1/161");
        TransportMapping transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        USM usm = new USM(SecurityProtocols.getInstance(),
                new OctetString(MPv3.createLocalEngineID()), 0);

        SecurityModels.getInstance().addSecurityModel(usm);
        transport.listen();

        snmp.getUSM().addUser(new OctetString("operator"),
                new UsmUser(new OctetString("operator"),
                        AuthMD5.ID,
                        new OctetString("AuthPassw0rd"),
                        PrivDES.ID,
                        new OctetString("EncryptionPassw0rd")));

        UserTarget target = new UserTarget();
        target.setAddress(targetAddress);
        target.setRetries(1);
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version3);
        target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
        target.setSecurityName(new OctetString("operator"));

        ScopedPDU pdu = new ScopedPDU();
        pdu.add(new VariableBinding(new OID("1.3.6")));
        pdu.setType(PDU.GETNEXT);

        ResponseEvent response = snmp.send(pdu, target);
        PDU responsePDU = response.getResponse();

        return response;
    }
}
