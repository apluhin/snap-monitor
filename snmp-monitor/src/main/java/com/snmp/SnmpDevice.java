package com.snmp;

import com.enums.TypeEncrypt;
import com.enums.TypeHash;
import com.enums.TypeV;

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

    public TypeEncrypt getTypeEncrypt() {
        return versionSnmp.typeEncrypt();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnmpDevice that = (SnmpDevice) o;

        return versionSnmp != null ? versionSnmp.equals(that.versionSnmp) : that.versionSnmp == null;

    }

    @Override
    public int hashCode() {
        return versionSnmp != null ? versionSnmp.hashCode() : 0;
    }
}
