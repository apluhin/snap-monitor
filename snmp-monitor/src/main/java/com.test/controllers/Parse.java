package com.test.controllers;

import com.test.entity.Device;
import com.test.build.ParserXml;
import com.test.mib.Command;
import com.test.mib.ParseMib;
import org.dom4j.DocumentException;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parse {



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
