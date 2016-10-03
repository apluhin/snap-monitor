package mib;

import org.junit.Assert;
import org.junit.Test;
import org.snmp4j.PDU;

import java.io.File;
import java.util.List;

public class ParseMibTest {

    @Test
    public void testParseCommand() throws Exception {
        File file = new File(System.getProperty("user.home") + "/reports", "MIB.cvs");
        List<Command> commands = new ParseMib().parseCvs(file);
        Command command = commands.get(0);
        Assert.assertEquals(command.getName(), "sysName");
        Assert.assertEquals(command.getOid(), ".1.3.6.1.2.1.1.5");
        Assert.assertEquals(commands.get(1).getName(), "upTime");
        Assert.assertEquals(command.getTypeRequest(), PDU.GETNEXT);

    }
}