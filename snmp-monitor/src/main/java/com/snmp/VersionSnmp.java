package com.snmp;

import com.enums.TypeEncrypt;
import com.enums.TypeHash;

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
