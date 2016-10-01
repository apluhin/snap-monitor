package build;

import main.Device;
import main.ParserXml;
import org.junit.Assert;
import org.junit.Test;
import org.snmp4j.Target;
import snmp.SnmpDevice;

import java.io.File;
import java.util.List;

public class BuildTargetTest {

    @Test
    public void testGenerateTargetSnmpV1() throws Exception {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home") + "/reports", "snmp1.xml"));
        List<Device> devices = parserXml.treeWalk();

        BuildTarget buildTarget = new BuildTarget();
        Target generate = buildTarget.generate(devices.get(0));
        SnmpDevice snmpDevice = devices.get(0).getSnmpDevice();
        Assert.assertEquals(snmpDevice.getSecurityLevel(), generate.getVersion());
        Assert.assertEquals(devices.get(0).getAddress().getHostAddress() + "/161", generate.getAddress().toString());
        Assert.assertEquals("public", generate.getSecurityName().toString());
    }

    @Test
    public void testGenerateTargetSnmpV3() throws Exception {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
        List<Device> devices = parserXml.treeWalk();
        SnmpDevice snmpDevice = devices.get(0).getSnmpDevice();
        Target generate = new BuildTarget().generate(devices.get(0));
        Assert.assertEquals(generate.getSecurityLevel(), snmpDevice.getSecurityLevel());
        Assert.assertEquals(devices.get(0).getAddress().getHostAddress() + "/161", generate.getAddress().toString());
        Assert.assertEquals(generate.getVersion(), snmpDevice.getVersion());
        Assert.assertEquals(generate.getSecurityName().toString(), snmpDevice.getUsername());

    }
}