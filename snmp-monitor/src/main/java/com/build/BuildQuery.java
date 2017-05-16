package com.build;

import com.entity.Device;
import com.snmp.GenerateUsmUser;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.OctetString;

import java.io.IOException;

/**
 * Created by alexey on 29.09.16.
 */
public class BuildQuery {
    private final Device device;

    public BuildQuery(Device device) {
        this.device = device;
    }

    public Snmp buildQuery(Snmp snmp, TransportMapping transport) throws IOException {


        if(device.getSnmpDevice().getVersion() == SnmpConstants.version3) {setUsm(snmp);}
        if(!transport.isListening()) {
            transport.listen();
        }
        return snmp;
    }

    private void setUsm(Snmp snmp) {
        USM usm = new USM(SecurityProtocols.getInstance(),
                new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);

        snmp.getUSM().addUser(new GenerateUsmUser(device.getSnmpDevice()).generateUsmUser());

    }
}
