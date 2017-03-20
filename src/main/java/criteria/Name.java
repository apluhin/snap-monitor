package criteria;

import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;

public class Name extends AbstractTask {

    private static final Logger logger = LoggerFactory.getLogger(FreeRam.class);

    public Name(Command command) {
        super(command);
    }

    @Override
    public Object execute(Device device)  {
        ResponseEvent responseEvent;
        try {
            responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
            logger.info(device.getAddress() + " name " + Util.getVariable(responseEvent).toString());
            return Util.getVariable(responseEvent).toString();
        } catch (IOException e) {
            throw new NullPointerException("need handle");
        }
    }
}
