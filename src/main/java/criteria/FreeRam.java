package criteria;

import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;
import java.util.function.Function;

public class FreeRam extends AbstractCriteria implements Critirea {

    private static final Logger logger = LoggerFactory.getLogger(FreeRam.class);

    private final Function<Long, Boolean> checkRam;

    public FreeRam(Command command, Function<Long, Boolean> checkRam) {
        super(command);
        this.checkRam = checkRam;
    }

    @Override
    public Object execute(Device device) throws IOException {
        //TODO handle exception
        return checkFreeRam(device);
    }

    private Object checkFreeRam(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
        long i = (long) Util.getVariable(responseEvent);
        i = i / (1024);
        logger.info(device.getAddress() + " free space RAM " + i + "Kb");
        if(!checkRam.apply(i)) {
            logger.debug("Ram space to small");
        }
        return i;
    }


}
