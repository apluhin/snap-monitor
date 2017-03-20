package criteria;

import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class FreeRam extends AbstractTask {

    private static final Logger logger = LoggerFactory.getLogger(FreeRam.class);



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

    private Object checkFreeRam(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
        long i =  Util.getVariable(responseEvent).toLong();
        i = i / (1024);
        logger.info(device.getAddress() + " free space RAM " + i + "Kb");
        return i;
    }


}
