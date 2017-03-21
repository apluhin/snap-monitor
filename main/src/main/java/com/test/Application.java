package com.test;

import com.test.controllers.Monitor;
import com.test.controllers.Parse;
import com.test.entity.Device;
import com.test.enums.Vendor;
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
    Monitor monitor;


    public static void main(String[] args) throws InterruptedException {


//        List<Task> tasks = new ArrayList<>();

        SpringApplication.run(Application.class, args);
//        Thread.sleep(15000);

    }

    @PostConstruct
    public void startMonitor() {
        monitor.beginExecute();
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        List<Device> device = Parse.getDevice(xml);
        monitor.addDeviceOnExecute(device.get(0), (Vendor.valueOf(device.get(0).getVendor().toUpperCase()).getCpu1MinuteTask()));
    }
}