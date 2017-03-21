package com.test.criteria;

import com.test.entity.Device;
import com.test.mib.Command;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class Name extends AbstractTask {



    public Name(Command command) {
        super(command);
    }

    @Override
    public Object execute(Device device)  {
        ResponseEvent responseEvent;
        try {
            responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
            String load = Util.getVariable(responseEvent).toString();
            System.out.println(device.getAddress() + " name " + load);
            return Util.getVariable(responseEvent).toString();
        } catch (IOException e) {
            throw new NullPointerException("need handle");
        }
    }

    @Override
    public String getName() {
        return "sysName";
    }
}
