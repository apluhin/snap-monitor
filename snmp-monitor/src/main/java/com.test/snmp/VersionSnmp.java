package com.test.snmp;

import com.test.enums.TypeEncypt;
import com.test.enums.TypeHash;

public interface VersionSnmp {
    String getEncryptionPassword();

    String getHashPassword();

    String getUsername();

    TypeHash typeHash();

    TypeEncypt typeEncript();

    String getCommunity();

    int getVersion();

    int getSecurityLevel();


}
