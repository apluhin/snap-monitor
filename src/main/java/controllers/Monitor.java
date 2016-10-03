package controllers;

import criteria.CpuLoad;
import criteria.Critirea;
import main.Device;
import main.ParserXml;
import mib.Command;
import mib.ParseMib;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        List<Critirea> critireas = new ArrayList<>();
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home") + "/reports", "snmp.xml"));
        File file = new File(System.getProperty("user.home") + "/reports", "MIB.cvs");
        List<Command> commands = ParseMib.parseCvs(file);
        List<Device> devices = parserXml.treeWalk();
        Function<Integer, Boolean> function = getFunc(3);
        CpuLoad cpuLoad = new CpuLoad(commands.get(2), function);
        critireas.add(cpuLoad);

    }

    private static Function<Integer,Boolean> getFunc(int i) {
        return s -> s < i;
    }
}
