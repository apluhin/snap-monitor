package criteria;

import main.Device;
import main.Sender;
import mib.Command;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;

import java.io.IOException;
import java.util.function.Function;

public class CpuLoad implements Critirea {

    private final Command command;
    private final Sender sender;
    private final Function<Integer, Boolean> criteria;

    public CpuLoad(Command commandForCheckCpu, Function<Integer, Boolean> criteria) {
        this.command = commandForCheckCpu;
        this.criteria = criteria;
        this.sender = new Sender();
    }

    public int checkCpuLoad(Device device) throws IOException {
        ResponseEvent responseEvent = sender.sendRequest(device, new OID(command.getOid()), command.getTypeRequest());
        int i = responseEvent.getResponse().getVariableBindings().get(0).getVariable().toInt();
        if(criteria.apply(i)) {
            //logger must be
            System.out.println(device.getAddress() + " load cpu " + i + "%");
            return i;
        } else {
            //logger
            throw new RuntimeException("Alarm");
        }
    }

    @Override
    public Object execute(Device device) {
        int i = 0;
        try {
            i = checkCpuLoad(device);
        } catch (IOException e) {
           //TODO logger must be
        }
        return i;
    }
}
