package com.test;

import com.test.controllers.Parse;
import com.test.entity.Device;
import com.test.notification.HealthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.test")
public class Application {

    @Autowired
    TestBean testBean;
    @Autowired
    Test1 test1;

    @Autowired
    Monitor monitor;
    @Autowired
    HealthChecker healthChecker;


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void startMonitor() {
        monitor.beginExecute();
        healthChecker.startCheck();
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        List<Device> device = Parse.getDevice(xml);
//           test1.testSave();
        monitor.addDeviceOnExecute(device.get(0));
        monitor.addDeviceOnExecute(device.get(1));
        monitor.addDeviceOnExecute(device.get(2));
        monitor.addDeviceOnExecute(device.get(3));
        monitor.addDeviceOnExecute(device.get(4));
        monitor.addLoad();
        //  testBean.add();
    }


}