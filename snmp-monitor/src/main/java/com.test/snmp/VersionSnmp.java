package com.test.snmp;

import com.test.enums.TypeEncrypt;
import com.test.enums.TypeHash;

public interface VersionSnmp {
    String getEncryptionPassword();

    String getHashPassword();

    String getUsername();

    TypeHash typeHash();

    TypeEncrypt typeEncrypt();

    String getCommunity();

    int getVersion();

    int getSecurityLevel();


}
