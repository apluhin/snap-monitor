package controllers;

import criteria.Critirea;
import criteria.FreeRam;
import main.Device;
import main.ParserXml;
import mib.Command;
import mib.ParseMib;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

public class Monitor {
    private final List<Device> deviceList;
    private final List<Command> commands;

    public Monitor(List<Device> deviceList, List<Command> commands) {
        this.deviceList = deviceList;
        this.commands = commands;
    }

    public void addDevice(Device device) {
        deviceList.add(device);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public static void main(String[] args) throws DocumentException, FileNotFoundException, InterruptedException {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
        File file = new File(System.getProperty("user.home") + "/reports", "MIB.cvs");
        Monitor monitor = new Monitor(parserXml.treeWalk(), ParseMib.parseCvs(file));

        Function<Long, Boolean> fun = s -> s > 1000;
        Critirea critirea = new FreeRam(monitor.commands.get(3), fun);


        while (true) {
            critirea.execute(monitor.deviceList.get(0));
            Thread.sleep(10000);
        }

    }

    private static Function<Integer, Boolean> getFunc(int i) {
        return s -> s < i;
    }
}
