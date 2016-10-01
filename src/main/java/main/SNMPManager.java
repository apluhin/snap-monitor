package main;

import build.BuildPdu;
import build.BuildQuery;
import build.BuildTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SNMPManager {

    public static void main(String[] args) throws Exception {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home")  + "/reports", "snmp3.xml"));
        List<Device> devices = parserXml.treeWalk();
        ResponseEvent responseEvent = new SNMPManager().sendRequest(devices.get(0), new OID(".1.3.6.1.2.1.1.3.0"), PDU.GET);
    }


    public ResponseEvent sendRequest(Device device, OID oid, int typePdu) throws IOException {
        BuildQuery request = new BuildQuery(device);
        Snmp snmp = request.buildQuery();
        Target target = new BuildTarget().generate(device);
        PDU pdu = new BuildPdu().getGeneratePdu(target.getVersion(), oid, typePdu);
        return snmp.send(pdu, target);
    }
}
