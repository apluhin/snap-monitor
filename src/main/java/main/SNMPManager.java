package main;

import build.BuildPdu;
import build.BuildQuery;
import build.BuildTarget;
import mib.Command;
import mib.ParseMib;
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
        List<Command> commands = ParseMib.parseCvs(new File(System.getProperty("user.home") + "/reports", "MIB.cvs"));
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home")  + "/reports", "snmp.xml"));
        List<Device> devices = parserXml.treeWalk();                                        //.1.3.6.1.2.1.1.5
        ResponseEvent responseEvent = new SNMPManager().sendRequest(devices.get(0), new OID(commands.get(0).getOid()), PDU.GETNEXT);
        System.out.println(responseEvent.getResponse().getVariableBindings());
    }


    public ResponseEvent sendRequest(Device device, OID oid, int typePdu) throws IOException {
        BuildQuery request = new BuildQuery(device);
        Snmp snmp = request.buildQuery();
        Target target = new BuildTarget().generate(device);
        PDU pdu = new BuildPdu().getGeneratePdu(target.getVersion(), oid, typePdu);
        return snmp.send(pdu, target);
    }
}
