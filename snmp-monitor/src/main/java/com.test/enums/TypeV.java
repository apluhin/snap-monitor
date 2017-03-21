package com.test.enums;

import com.test.snmp.SnmpV3;
import com.test.snmp.VersionSnmp;
import com.test.snmp.SnmpV1;
import com.test.snmp.SnmpV2;

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
