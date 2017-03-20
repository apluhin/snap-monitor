package criteria;

import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class CpuLoad extends AbstractTask {

    private static final Logger logger = LoggerFactory.getLogger(CpuLoad.class);



    public CpuLoad(Command commandForCheckCpu) {
        super(commandForCheckCpu);
    }

    public int checkCpuLoad(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, (command.getOid()), command.getTypeRequest());
        int i = (int) Util.getVariable(responseEvent).toInt();
        logger.info(device.getAddress() + " load cpu " + i + "%");
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
}
