import org.snmp4j.CommunityTarget;
import org.snmp4j.Target;
import org.snmp4j.UserTarget;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import snmp.SnmpDevice;

/**
 * Created by alexey on 29.09.16.
 */
public class BuildTarget {

    public Target generate(Device device) {
        Target target;
        if(device.getSnmpDevice().getVersion() == SnmpConstants.version3) {
            target = generateSecureTarget(device);
        } else {
            target = generateCommnutiTarget(device);
        }
        target.setAddress(GenericAddress.parse("udp:" + device.getAddress().toString() + "/161"));
        target.setRetries(3);
        target.setTimeout(500);
        target.setVersion(device.getSnmpDevice().getVersion());

        return target;
    }

    private Target generateSecureTarget(Device device) {
        Target target = new UserTarget();
        target.setVersion(device.getSnmpDevice().getVersion());
        target.setSecurityLevel(device.getSnmpDevice().getSecurityLevel());
        target.setSecurityName(new OctetString(device.getSnmpDevice().getUsername()));
        return target;
    }

    private Target generateCommnutiTarget(Device device) {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(device.getSnmpDevice().getCommunity()));
        return target;
    }
}
