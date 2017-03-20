package build;

public class BuildTargetTest {

//    @Test
//    public void testGenerateTargetSnmpV1() throws Exception {
//        List<Device> devices = ParserXml.treeWalk(new File(System.getProperty("user.home") + "/reports", "snmp1.xml"));
//
//        BuildTarget buildTarget = new BuildTarget();
//        Target generate = buildTarget.generate(devices.get(0));
//        SnmpDevice snmpDevice = devices.get(0).getSnmpDevice();
//        Assert.assertEquals(snmpDevice.getSecurityLevel(), generate.getVersion());
//        Assert.assertEquals(devices.get(0).getAddress().getHostAddress() + "/161", generate.getAddress().toString());
//        Assert.assertEquals("public", generate.getSecurityName().toString());
//    }
//
//    @Test
//    public void testGenerateTargetSnmpV3() throws Exception {
//        List<Device> devices = ParserXml.treeWalk(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
//        SnmpDevice snmpDevice = devices.get(0).getSnmpDevice();
//        Target generate = new BuildTarget().generate(devices.get(0));
//        Assert.assertEquals(generate.getSecurityLevel(), snmpDevice.getSecurityLevel());
//        Assert.assertEquals(devices.get(0).getAddress().getHostAddress() + "/161", generate.getAddress().toString());
//        Assert.assertEquals(generate.getVersion(), snmpDevice.getVersion());
//        Assert.assertEquals(generate.getSecurityName().toString(), snmpDevice.getUsername());
//
//    }
//
//    @Test
//    public void testGenerateTargetSnmpV3WithoutDes() throws Exception {
//        List<Device> devices = ParserXml.treeWalk(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
//        SnmpDevice snmpDevice = devices.get(1).getSnmpDevice();
//        Target generate = new BuildTarget().generate(devices.get(1));
//        Assert.assertEquals(generate.getSecurityLevel(), snmpDevice.getSecurityLevel());
//        Assert.assertEquals(devices.get(1).getAddress().getHostAddress() + "/161", generate.getAddress().toString());
//        Assert.assertEquals(generate.getVersion(), snmpDevice.getVersion());
//        Assert.assertEquals(generate.getSecurityName().toString(), snmpDevice.getUsername());
//
//    }


}