package main;

import build.BuildPdu;
import build.BuildQuery;
import build.BuildTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;

import java.io.IOException;

public class Sender {

    public ResponseEvent sendRequest(Device device, OID oid, int typePdu) throws IOException {
        BuildQuery request = new BuildQuery(device);
        Snmp snmp = request.buildQuery();
        Target target = new BuildTarget().generate(device);
        PDU pdu = new BuildPdu().getGeneratePdu(target.getVersion(), oid, typePdu);
        return snmp.send(pdu, target);
    }
}
