import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;

import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class TypeSnmpV3 implements TypeSnmp {

    private static int version = SnmpConstants.version3;

    private final String username;
    private final String encyptonPassword;
    private final String hashPassword;
    private final int securityLevel;

    public TypeSnmpV3(Map<String, String> type) {
        hashPassword = type.get("hashPassword");
        encyptonPassword = type.get("encryptionPassword");
        username = type.get("username");
        this.securityLevel = setSecurityLevel(type);
    }

    @Override
    public String getEncryptionPassword() {
        return encyptonPassword;
    }

    @Override
    public String getHashPassword() {
        return hashPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String typeHash() {
        return null;
    }

    @Override
    public String typeEncript() {
        return null;
    }

    @Override
    public String getCommunity() {
        throw new UnsupportedOperationException("Use Authentication v3");
    }



    public int setSecurityLevel(Map<String,String> map) {
        if (map.get("hashPassword") == null) return SecurityLevel.NOAUTH_NOPRIV;
        if (map.get("EncryptPassword") == null) return SecurityLevel.AUTH_NOPRIV;
        return SecurityLevel.AUTH_PRIV;
    }
}
