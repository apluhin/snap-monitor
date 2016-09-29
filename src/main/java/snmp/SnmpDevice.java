package snmp;

import enums.TypeV;
import enums.TypeEncypt;
import enums.TypeHash;

import java.util.Map;

public class SnmpDevice {

    private final VersionSnmp versionSnmp;


    public SnmpDevice(Map<String, String> map) {
        versionSnmp = TypeV.valueOf(map.get("version")).getType(map);
    }

    public String getEncryptionPassword() {
        return versionSnmp.getEncryptionPassword();
    }

    public String getHashPassword() {
        return versionSnmp.getHashPassword();
    }

    public String getUsername() {
        return versionSnmp.getUsername();
    }

    public TypeHash getTypeHash() {
        return versionSnmp.typeHash();
    }

    public TypeEncypt getTypeEncript() {
        return versionSnmp.typeEncript();
    }

    public String getCommunity() {
        return versionSnmp.getCommunity();
    }

    public int getVersion() {
        return versionSnmp.getVersion();
    }

    public int getSecurityLevel() {
        return versionSnmp.getSecurityLevel();
    }

    @Override
    public String toString() {
        return "snmp.SnmpDevice{" +
                "snmp=" + versionSnmp.toString() +
                '}';
    }
}
