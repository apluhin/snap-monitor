package controllers;

import main.Device;
import main.ParserXml;
import mib.Command;
import mib.ParseMib;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parse {

    private static final Logger logger = LoggerFactory.getLogger(Parse.class);

    public static List<Command> getCommands(File cvs) {
        List<Command> commands;
        try {
            commands = ParseMib.parseCvs(cvs);

        } catch (IOException e) {
            throw new RuntimeException("Error during parse", e.getCause());
        }
        return commands;
    }


    public static List<Device> getDevice(File xml) {
        List<Device> devices;
        try {

            devices = ParserXml.treeWalk(xml);

        } catch (DocumentException e) {
            throw new RuntimeException("Error during parse", e.getCause());
        }
        return devices;
    }

}
