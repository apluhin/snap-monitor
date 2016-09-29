import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.InetAddress;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ParserXmlTest {
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";
    Document document;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testOneDeviceSnmpV1() throws Exception {
        File file = new File(REPORTS_DIRECTORY, "snmp1.xml");
        SAXReader reader = new SAXReader();
        document = reader.read(file);
        Device device = ParserXml.treeWalk(document).get(0);
        assertEquals("public", device.getSnmp().getCommunity());
        assertEquals(InetAddress.getByName("192.168.0.10").toString(), device.getAddress().toString());
    }

    @Test
    public void testOneDeviceSnmpV3() throws Exception {
        File file = new File(REPORTS_DIRECTORY, "snmp.xml");
        SAXReader reader = new SAXReader();
        document = reader.read(file);
        Device device = ParserXml.treeWalk(document).get(0);
        assertEquals("operator", device.getSnmp().getUsername());
        assertEquals("Cisco", device.getVendor());
        assertEquals(InetAddress.getByName("192.168.0.1").toString(), device.getAddress().toString());
        assertEquals("EncryptionPassw0rd", device.getSnmp().getEncryptionPassword());
    }

    @Test
    public void testToDevicesSnmpV1AndSnmpV3() throws Exception {
        File file = new File(REPORTS_DIRECTORY, "snmp3.xml");
        SAXReader reader = new SAXReader();
        document = reader.read(file);
        List<Device> devices = ParserXml.treeWalk(document);
        assertEquals(devices.size(), 2);
        assertEquals(devices.get(0).getVendor(), "Huawei");
        assertEquals(devices.get(1).getVendor(), "Cisco");
        assertNotEquals(devices.get(0).getAddress().toString(), devices.get(1).getAddress().toString());

    }

    @After
    public void tearDown() throws Exception {

    }

}