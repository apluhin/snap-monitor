package com.controllers;

import com.build.BuildPdu;
import com.build.BuildQuery;
import com.build.BuildTarget;
import com.entity.Device;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class Sender {

    static Snmp snmp;
    static TransportMapping transport;

    static {
        try {
            transport = new DefaultUdpTransportMapping();
        } catch (IOException e) {
            e.printStackTrace();
        }
        snmp = new Snmp(transport);
    }


    public ResponseEvent sendRequest(Device device, String oid, int typePdu) throws IOException {
        BuildQuery request = new BuildQuery(device);
        request.buildQuery(snmp, transport);

        Target target = new BuildTarget().generate(device);
        PDU pdu = new BuildPdu().getGeneratePdu(target.getVersion(), new OID(oid), typePdu);
        ResponseEvent send = snmp.send(pdu, target);



        return snmp.send(pdu, target);
    }
}
