package snmp;

import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.OctetString;

public enum  EnumUsm {



    AUTH_PRIV("3") {
        @Override
        public UsmUser genetateUsm(SnmpDevice snmpDevice) {
            return null;
        }
    },
    AUTH_NOPRIV("2") {
        @Override
        public UsmUser genetateUsm(SnmpDevice snmpDevice) {
            UsmUser usmUser = new UsmUser(
                    new OctetString(snmpDevice.getUsername()),
                    snmpDevice.getTypeHash().getMethodHash(),
                    new OctetString(snmpDevice.getHashPassword()),
                    null,
                    null
            );
            return usmUser;
        }
    };

    EnumUsm(String s) {
        this.s = s;
    }

    public static EnumUsm getEnum(String s) {
        for (EnumUsm enumUsm : values()) {
            if(enumUsm.s.equals(s)) {
                return enumUsm;
            }
        }
        return null;
    }

    public abstract UsmUser genetateUsm(SnmpDevice snmpDevice);

    private final String s;
}
