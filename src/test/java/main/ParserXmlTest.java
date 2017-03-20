package main;

import org.dom4j.Document;
import org.junit.After;
import org.junit.Before;

public class ParserXmlTest {
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";
    Document document;

    @Before
    public void setUp() throws Exception {

    }
//
//    @Test
//    public void testOneDeviceSnmpV1() throws Exception {
//        File file = new File(REPORTS_DIRECTORY, "snmp1.xml");
//        Device device = ParserXml.treeWalk(file).get(0);
//        assertEquals("public", device.getSnmpDevice().getCommunity());
//        assertEquals(InetAddress.getByName("192.168.0.10").toString(), device.getAddress().toString());
//    }
//
//    @Test
//    public void testOneDeviceSnmpV3() throws Exception {
//        File file = new File(REPORTS_DIRECTORY, "snmp.xml");
//        Device device = ParserXml.treeWalk(file).get(0);
//        assertEquals("operator", device.getSnmpDevice().getUsername());
//        assertEquals("CISCO", device.getVendor());
//        assertEquals(InetAddress.getByName("192.168.0.1").toString(), device.getAddress().toString());
//        assertEquals("EncryptionPassw0rd", device.getSnmpDevice().getEncryptionPassword());
//    }
//
//    @Test
//    public void testToDevicesSnmpV1AndSnmpV3() throws Exception {
//        File file = new File(REPORTS_DIRECTORY, "snmp3.xml");
//        List<Device> devices = ParserXml.treeWalk(file);
//        assertEquals(devices.size(), 2);
//        assertEquals(devices.get(0).getVendor(), "HUAWEI");
//        assertEquals(devices.get(1).getVendor(), "CISCO");
//        assertNotEquals(devices.get(0).getAddress().toString(), devices.get(1).getAddress().toString());
//
//    }


    @After
    public void tearDown() throws Exception {

    }

}