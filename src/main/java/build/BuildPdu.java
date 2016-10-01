package build;

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

public class BuildPdu {

    public PDU getGeneratePdu(int targetVersion, OID oid, int versionPdu) {
        PDU pdu;
        pdu = (targetVersion == SnmpConstants.version3) ? new ScopedPDU() : new PDU();
        pdu.add(new VariableBinding(oid));
        pdu.setType(versionPdu);
        return pdu;
    }
}
