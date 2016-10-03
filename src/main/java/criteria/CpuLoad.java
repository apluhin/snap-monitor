package criteria;

import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

import java.io.IOException;
import java.util.function.Function;

public class CpuLoad extends AbstractCriteria implements Critirea {

    private static final Logger logger = LoggerFactory.getLogger(CpuLoad.class);

    private final Function<Integer, Boolean> criteria;

    public CpuLoad(Command commandForCheckCpu, Function<Integer, Boolean> criteria) {
        super(commandForCheckCpu);
        this.criteria = criteria;
    }

    public int checkCpuLoad(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, (command.getOid()), command.getTypeRequest());
        int i = (int) Util.getVariable(responseEvent);
        logger.debug(device.getAddress() + " load cpu " + i + "%");
        if(!criteria.apply(i)) {
            logger.debug("CPU overload");
        }
        return i;
    }

    @Override
    public Object execute(Device device) throws IOException {
        return checkCpuLoad(device);
    }
}
