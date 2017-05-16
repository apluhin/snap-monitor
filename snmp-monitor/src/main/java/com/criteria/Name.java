package com.criteria;

import com.entity.Device;
import com.mib.Command;
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
            return Util.getVariable(responseEvent).toString();
        } catch (IOException | NullPointerException e) {
            throw new NullPointerException("need handle");
        }
    }

    @Override
    public String getName() {
        return "sysName";
    }
}
