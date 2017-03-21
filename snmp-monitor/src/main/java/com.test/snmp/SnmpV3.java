package com.test.snmp;

import com.test.enums.TypeEncypt;
import com.test.enums.TypeHash;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;

import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class SnmpV3 implements VersionSnmp {

    private static int version = SnmpConstants.version3;

    private final String username;
    private final String encyptonPassword;
    private final String hashPassword;
    private final int securityLevel;

    private final TypeHash typeHash;
    private final TypeEncypt typeEncypt;

    public SnmpV3(Map<String, String> type) {
        hashPassword = type.get("hashPassword");
        encyptonPassword = type.get("encryptionPassword");
        username = type.get("username");
        securityLevel = setSecurityLevel(type);
        typeHash = getTypeHash(type);
        typeEncypt = getTypeEncript(type);
    }

    private TypeEncypt getTypeEncript(Map<String, String> type) {
        if(type.get("typeEncript") == null) return null;
        return TypeEncypt.valueOf(type.get("typeEncript"));
    }

    private TypeHash getTypeHash(Map<String, String> type) {
        if(type.get("typeHash") == null) return null;
        return TypeHash.valueOf(type.get("typeHash"));
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
    public TypeHash typeHash() {
        return typeHash;
    }

    @Override
    public TypeEncypt typeEncript() {
        return typeEncypt;
    }

    @Override
    public String getCommunity() {
        throw new UnsupportedOperationException("Use Authentication v3");
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }


    public int setSecurityLevel(Map<String,String> map) {
        if (username == null) return SecurityLevel.NOAUTH_NOPRIV;
        if (encyptonPassword == null) return SecurityLevel.AUTH_NOPRIV;
        return SecurityLevel.AUTH_PRIV;
    }


    @Override
    public String toString() {
        return "SnmpV3{" +
                "username='" + username + '\'' +
                ", encyptonPassword='" + encyptonPassword + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", securityLevel=" + securityLevel +
                ", typeHash=" + typeHash +
                ", typeEncypt=" + typeEncypt +
                '}';
    }
}
