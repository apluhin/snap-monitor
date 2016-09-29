import java.util.Map;

public class Snmp {

    private final TypeSnmp typeSnmp;


    public Snmp(Map<String, String> map) {
        typeSnmp = Type.valueOf(map.get("version")).getType(map);
    }

    public String getEncryptionPassword() {
        return typeSnmp.getEncryptionPassword();
    }

    public String getHashPassword() {
        return typeSnmp.getHashPassword();
    }

    public String getUsername() {
        return typeSnmp.getUsername();
    }

    public String getTypeHash() {
        return typeSnmp.typeHash();
    }

    public String getTypeEncript() {
        return typeSnmp.typeEncript();
    }

    public String getCommunity() {
        return typeSnmp.getCommunity();
    }

}
