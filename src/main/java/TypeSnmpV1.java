import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class TypeSnmpV1 implements TypeSnmp {

    private final String community;

    public TypeSnmpV1(Map<String, String> type) {
        community = type.get("community");
    }

    @Override
    public String getEncryptionPassword() {
        throw new UnsupportedOperationException("This version of snmp doest support");
    }

    @Override
    public String getHashPassword() {
        throw new UnsupportedOperationException("This version of snmp doest support");    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("This version of snmp doest support");    }

    @Override
    public TypeHash typeHash() {
        throw new UnsupportedOperationException("This version of snmp doest support");    }

    @Override
    public TypeEncypt typeEncript() {
        throw new UnsupportedOperationException("This version of snmp doest support");    }

    @Override
    public String getCommunity() {
       return community;
    }
}
