package criteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Variable;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static Variable getVariable(ResponseEvent responseEvent) {

        return responseEvent.getResponse().getVariableBindings().get(0).getVariable();


    }
}
