package com.criteria;

import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Variable;

public class Util {



    public static Variable getVariable(ResponseEvent responseEvent) {
        try {
            return responseEvent.getResponse().getVariableBindings().get(0).getVariable();
        } catch (Exception e) {
            throw new RuntimeException("Can't get response", e);
        }
    }
}
