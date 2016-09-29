import org.snmp4j.*;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

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


        // create the target
        UserTarget target = new UserTarget();
        target.setAddress(targetAddress);
        target.setRetries(1);
        target.setTimeout(1000);
        target.setVersion(SnmpConstants.version3);
        target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
        target.setSecurityName(new OctetString("operator"));


        // create the PDU
        ScopedPDU pdu = new ScopedPDU();
        pdu.add(new VariableBinding(new OID("1.3.6")));
        pdu.setType(PDU.GETNEXT);

        // send the PDU
        ResponseEvent response = snmp.send(pdu, target);
        // extract the response PDU (could be null if timed out)
        PDU responsePDU = response.getResponse();
        // extract the address used by the agent to send the response:
        System.out.println(response.getResponse().getVariableBindings());


        //   oidValue = new BufferedReader(new InputStreamReader(System.in)).readLine();
//        System.out.println("SNMP GET Demo");
//        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
//                MPv3.createLocalEngineID()),0);
//
//         // Create TransportMapping and Listen
//        TransportMapping transport = new DefaultUdpTransportMapping();
//        transport.listen();
//
//        // Create Target Address object
//        CommunityTarget comtarget = new CommunityTarget();
//
//        comtarget.setCommunity(new OctetString(community));
//        comtarget.setVersion(snmpVersion);
//        comtarget.setAddress(new UdpAddress(ipAddress + "/" + port));
//        comtarget.setRetries(1);
//        comtarget.setTimeout(1000);
//        comtarget.setSecurityModel(3);
//
//        // Create the PDU object
//        ScopedPDU pdu = new ScopedPDU();
//        pdu.add(new VariableBinding(new OID(oidValue)));
//        pdu.setType(PDU.GET);
//        pdu.setRequestID(new Integer32(1));
//
//        // Create Snmp object for sending data to Agent
//        Snmp snmp = new Snmp(transport);
//        System.out.println("Sending Request to Agent...");
//        ResponseEvent response = snmp.get(pdu, comtarget);
//
//        // Process Agent Response
//        if (response != null)
//        {
//            System.out.println("Got Response from Agent");
//            PDU responsePDU = response.getResponse();
//
//            if (responsePDU != null)
//            {
//                int errorStatus = responsePDU.getErrorStatus();
//                int errorIndex = responsePDU.getErrorIndex();
//                String errorStatusText = responsePDU.getErrorStatusText();
//
//                if (errorStatus == PDU.noError)
//                {
//                    System.out.println("Snmp Get Response = " + responsePDU.getVariableBindings());
//                }
//                else
//                {
//                    System.out.println("Error: Request Failed");
//                    System.out.println("Error Status = " + errorStatus);
//                    System.out.println("Error Index = " + errorIndex);
//                    System.out.println("Error Status Text = " + errorStatusText);
//                }
//            }
//            else
//            {
//                System.out.println("Error: Response PDU is null");
//            }
//        }
//        else
//        {
//            System.out.println("Error: Agent Timeout... ");
//        }
//        snmp.close();
    }
}
