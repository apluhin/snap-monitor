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

    public static Monitor parseAll(File xml, File cvs) {
        Monitor monitor;
        try {
            List<Command> commands = ParseMib.parseCvs(cvs);
            List<Device> devices = ParserXml.treeWalk(xml);
            monitor = new Monitor(devices, commands);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Error during parse", e.getCause());
        }
        return monitor;
    }

}
