package enums;

import snmp.TypeSnmp;
import snmp.TypeSnmpV1;
import snmp.TypeSnmpV2;
import snmp.TypeSnmpV3;

import java.util.Map;

public enum Type  {

    v1 {
        @Override
        public TypeSnmp getType(Map<String, String> type) {
            return new TypeSnmpV1(type);
        }
    },
    v2 {
        @Override
        public TypeSnmp getType(Map<String, String> type) {
            return new TypeSnmpV2(type);
        }
    },
    v3 {
        @Override
        public TypeSnmp getType(Map<String, String> type) {
            return new TypeSnmpV3(type);
        }
    };


    public abstract TypeSnmp getType(Map<String, String> type);


}
