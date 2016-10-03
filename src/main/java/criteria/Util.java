package criteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.event.ResponseEvent;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static Object  getVariable (ResponseEvent responseEvent) {
        try {
            return responseEvent.getResponse().getVariableBindings().get(0).getVariable();
        } catch (NullPointerException e) {
            throw new RuntimeException("Can't find device");
        }
    }
}
