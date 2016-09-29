package snmp;

import enums.TypeEncypt;
import enums.TypeHash;
import org.snmp4j.smi.OID;

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
