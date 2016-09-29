import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class TypeSnmpV2 implements TypeSnmp {
    public TypeSnmpV2(Map<String, String> type) {
    }

    @Override
    public String getEncryptionPassword() {
        return null;
    }

    @Override
    public String getHashPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return null;
    }
}
