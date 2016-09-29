package snmp;

import enums.Type;
import enums.TypeEncypt;
import enums.TypeHash;
import snmp.TypeSnmp;

import java.util.Map;

public class SnmpDevice {

    private final TypeSnmp typeSnmp;


    public SnmpDevice(Map<String, String> map) {
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

    public int getVersion() {
        return typeSnmp.getVersion();
    }

    public int getSecurityLevel() {
        return typeSnmp.getSecurityLevel();
    }

    @Override
    public String toString() {
        return "snmp.SnmpDevice{" +
                "snmp=" + typeSnmp.toString() +
                '}';
    }
}
