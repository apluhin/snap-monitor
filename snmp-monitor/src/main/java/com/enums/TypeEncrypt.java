package com.enums;

import org.snmp4j.security.PrivDES;
import org.snmp4j.smi.OID;

public enum TypeEncrypt {
    DES {
        @Override
        public OID getMethodEncrypt() {
            return PrivDES.ID;
        }
    };


    public abstract OID getMethodEncrypt();
}
