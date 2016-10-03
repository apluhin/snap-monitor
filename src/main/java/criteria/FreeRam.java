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
    public Object execute(Device device) {
        //TODO handle exception
        Object o = null;
        try {
            o = checkFreeRam(device);
        } catch (IOException e) {
            logger.error("Error during send request");
        }
        return o;
    }

    private Object checkFreeRam(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, command.getOid(), command.getTypeRequest());
        long i = responseEvent.getResponse().getVariableBindings().get(0).getVariable().toLong();
        i = i / (1024);
        logger.info(device.getAddress() + " free space RAM " + i + "Kb");
        if(!checkRam.apply(i)) {
            logger.error("Ram space to small");
        }
        return i;
    }
}
