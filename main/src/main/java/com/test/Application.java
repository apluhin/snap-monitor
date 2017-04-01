package com.test;

import com.test.controllers.Parse;
import com.test.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages = "com.test")
public class Application {

    @Autowired
    TestBean testBean;

    @Autowired
    Monitor monitor;


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void startMonitor() {
        monitor.beginExecute();

        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        List<Device> device = Parse.getDevice(xml);
        monitor.addDeviceOnExecute(device.get(0));
        monitor.addDeviceOnExecute(device.get(1));
        //      monitor.addLoad();
        // testBean.add();
    }
}