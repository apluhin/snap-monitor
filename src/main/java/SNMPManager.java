import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SNMPManager {

    public static void main(String[] args) throws Exception {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home")  + "/reports", "snmp3.xml"));
        List<Device> devices = parserXml.treeWalk();
        ResponseEvent responseEvent = new SNMPManager().sendRequest(devices.get(0));
    }


    public ResponseEvent sendRequest(Device device) throws IOException {
        BuildQuery request = new BuildQuery(device);
        Snmp snmp = request.buildQuery();
        Target target = new BuildTarget().generate(device);
        //TODO PDU generate
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID("1.3.6")));
        pdu.setType(PDU.GETNEXT);
        ResponseEvent response = snmp.send(pdu, target);
        PDU responsePDU = response.getResponse();
        return response;
    }
}
