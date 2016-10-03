package main;

import build.BuildQuery;
import controllers.Parse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.snmp4j.Snmp;
import org.snmp4j.security.UsmUser;
import org.snmp4j.security.UsmUserEntry;
import org.snmp4j.smi.OctetString;
import snmp.SnmpDevice;

import java.io.File;
import java.util.List;

public class SenderTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testBuildQuerySnmpV3() throws Exception {
        List<Device> devices = ParserXml.treeWalk(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
        Snmp snmp1 = new BuildQuery(devices.get(0)).buildQuery();

        UsmUserEntry user = snmp1.getUSM().getUserTable().getUser(new OctetString("operator"));
        UsmUser usmUser = user.getUsmUser();
        SnmpDevice snmpDevice = devices.get(0).getSnmpDevice();
        Assert.assertEquals(usmUser.getSecurityName().toString(), snmpDevice.getUsername());
        Assert.assertEquals(usmUser.getPrivacyProtocol(), snmpDevice.getTypeEncript().getMethodEncrypt());
        Assert.assertEquals(usmUser.getAuthenticationPassphrase().toString(), snmpDevice.getHashPassword());

    }

    @Test
    public void testBuildQuerySnmpV1() throws Exception {
        List<Device> devices = ParserXml.treeWalk(new File(System.getProperty("user.home") + "/reports", "snmp1.xml"));
        Snmp snmp1 = new BuildQuery(devices.get(0)).buildQuery();
        Assert.assertEquals(snmp1.getUSM(), null);
    }

    @After
    public void tearDown() throws Exception {

    }

}