package snmp;

import enums.Type;
import enums.TypeEncypt;
import enums.TypeHash;
import snmp.TypeSnmp;

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

    public TypeHash getTypeHash() {
        return typeSnmp.typeHash();
    }

    public TypeEncypt getTypeEncript() {
        return typeSnmp.typeEncript();
    }

    public String getCommunity() {
        return typeSnmp.getCommunity();
    }

    @Override
    public String toString() {
        return "snmp.Snmp{" +
                "snmp=" + typeSnmp.toString() +
                '}';
    }
}
