package enums;

import org.snmp4j.security.AuthMD5;
import org.snmp4j.smi.OID;

public enum  TypeHash {
    MD5 {
        @Override
        public OID getMethodHash() {
            return AuthMD5.ID;
        }
    };

    public abstract OID getMethodHash();
}
