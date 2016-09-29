package snmp;

import enums.TypeEncypt;
import enums.TypeHash;

import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class SnmpV2 implements VersionSnmp {
    public SnmpV2(Map<String, String> type) {
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
    public TypeHash typeHash() {
        return null;
    }

    @Override
    public TypeEncypt typeEncript() {
        return null;
    }

    @Override
    public String getCommunity() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public int getSecurityLevel() {
        return 0;
    }
}
