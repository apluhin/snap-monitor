package controllers;

import criteria.CpuLoad;
import criteria.Critirea;
import criteria.FreeRam;
import main.Device;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Monitor {

    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

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

    public static void main(String[] args) throws InterruptedException {
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        File cvs = new File(System.getProperty("user.home") + "/reports", "MIB.cvs");
        Monitor monitor = Parse.parseAll(xml, cvs);

        Function<Long, Boolean> fun = s -> s > 1000;
        Function<Integer, Boolean> funCpu = s -> s < 5;
        Critirea ram = new FreeRam(monitor.commands.get(3), fun);
        Critirea cpu = new CpuLoad(monitor.commands.get(3), funCpu);

        Handler handler = new Handler();
        handler.addCriteria(monitor.getDeviceList().get(0), ram);
        handler.addCriteria(monitor.getDeviceList().get(0), cpu);

        while (true) {
            handler.executeAll();
            Thread.sleep(10000);
        }

    }

    public List<Device> getDeviceList() {
        return Collections.unmodifiableList(deviceList);
    }

    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
