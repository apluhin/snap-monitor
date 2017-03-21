package com.test.snmp;

import com.test.enums.TypeV;
import com.test.enums.TypeEncypt;
import com.test.enums.TypeHash;

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
        return "SnmpDevice{" +
                "com.test.snmp=" + versionSnmp.toString() +
                '}';
    }
}