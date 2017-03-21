package com.test.criteria;

import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Variable;

public class Util {



    public static Variable getVariable(ResponseEvent responseEvent) {

        return responseEvent.getResponse().getVariableBindings().get(0).getVariable();


    }
}
