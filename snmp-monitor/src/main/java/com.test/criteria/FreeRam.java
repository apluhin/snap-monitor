package com.test.criteria;

import com.test.entity.Device;
import com.test.mib.Command;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class FreeRam extends AbstractTask {


    public FreeRam(Command command) {
        super(command);

    }

    @Override
    public Object execute(Device device)  {
        //TODO handle exception
        try {
            return checkFreeRam(device);
        } catch (IOException e) {
            throw new RuntimeException("Current error", e);
        }
    }

    @Override
    public String getName() {
        return "FreeRam";
    }

    private Object checkFreeRam(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
        long i =  Util.getVariable(responseEvent).toLong();
        i = i / (1024);
        System.out.println(device.getAddress() + " free space RAM " + i + "Kb");
        return i;
    }


}
