package com.test.criteria;

import com.test.entity.Device;
import com.test.mib.Command;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class CpuLoad extends AbstractTask {




    public CpuLoad(Command commandForCheckCpu) {
        super(commandForCheckCpu);
    }

    public int checkCpuLoad(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, (command.getOid()), command.getTypeRequest());
        int i = (int) Util.getVariable(responseEvent).toInt();
        System.out.println(i);
        return i;
    }

    @Override
    public Object execute(Device device)  {
        try {
            return checkCpuLoad(device);
        } catch (IOException e) {
            throw new RuntimeException("err", e);
        }
    }

    @Override
    public String getName() {
        return "CpuLoad";
    }
}
