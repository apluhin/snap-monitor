package com.test.snmp;

import org.snmp4j.security.UsmUser;

public class GenerateUsmUser {
    private final SnmpDevice snmpDevice;

    public GenerateUsmUser(SnmpDevice snmpDevice) {
        this.snmpDevice = snmpDevice;
    }

    public UsmUser generateUsmUser() {
        return EnumUsm
                .getEnum(String.valueOf(snmpDevice.getSecurityLevel()))
                .genetateUsm(snmpDevice);
    }
}
