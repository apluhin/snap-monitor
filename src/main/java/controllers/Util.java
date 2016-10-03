package controllers;

import criteria.Critirea;
import main.Device;
import main.ParserXml;
import mib.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexey on 03.10.16.
 */
public class Util {

    public static Map<Device, List<Critirea>> generate(Monitor monitor) {
        Map<Device, List<Critirea>> map = new HashMap<>();
        List<Device> deviceList = monitor.getDeviceList();
        List<Command> commands = monitor.getCommands();
        for (Device device : deviceList) {
           map.putIfAbsent(device, new ArrayList<>());
          //  map.get(device).addAll(putCriteria(device, ));
        }
     return null;
    }

}
