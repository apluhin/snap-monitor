package com.enums;

import com.snmp.SnmpV1;
import com.snmp.SnmpV2;
import com.snmp.SnmpV3;
import com.snmp.VersionSnmp;

import java.util.Map;

public enum TypeV {

    v1 {
        @Override
        public VersionSnmp getType(Map<String, String> type) {
            return new SnmpV1(type);
        }
    },
    v2 {
        @Override
        public VersionSnmp getType(Map<String, String> type) {
            return new SnmpV2(type);
        }
    },
    v3 {
        @Override
        public VersionSnmp getType(Map<String, String> type) {
            return new SnmpV3(type);
        }
    };


    public abstract VersionSnmp getType(Map<String, String> type);


}
