import org.snmp4j.security.PrivDES;
import org.snmp4j.smi.OID;

public enum  TypeEncypt {
    DES {
        @Override
        public OID getTypeEncrypt() {
            return PrivDES.ID;
        }
    };

    public abstract OID getTypeEncrypt();
}
