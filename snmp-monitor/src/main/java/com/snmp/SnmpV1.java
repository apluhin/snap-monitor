package com.snmp;

import com.enums.TypeEncrypt;
import com.enums.TypeHash;
import org.snmp4j.mp.SnmpConstants;

import java.util.Map;

/**
 * Created by alexey on 29.09.16.
 */
public class SnmpV1 implements VersionSnmp {

    private final static int version = SnmpConstants.version1;
    private final String community;

    public SnmpV1(Map<String, String> type) {
        community = type.get("community");
    }

    @Override
    public String getEncryptionPassword() {
        throw new UnsupportedOperationException("This version of com.test.snmp doest support");
    }

    @Override
    public String getHashPassword() {
        throw new UnsupportedOperationException("This version of com.test.snmp doest support");    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("This version of com.test.snmp doest support");    }

    @Override
    public TypeHash typeHash() {
        throw new UnsupportedOperationException("This version of com.test.snmp doest support");    }

    @Override
    public TypeEncrypt typeEncrypt() {
        throw new UnsupportedOperationException("This version of com.test.snmp doest support");    }

    @Override
    public String getCommunity() {
       return community;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getSecurityLevel() {
        return 0;
    }
}
