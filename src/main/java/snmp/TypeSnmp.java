package snmp;

import enums.TypeEncypt;
import enums.TypeHash;

public interface TypeSnmp {
    String getEncryptionPassword();

    String getHashPassword();

    String getUsername();

    TypeHash typeHash();

    TypeEncypt typeEncript();

    String getCommunity();


}
