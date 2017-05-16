package com;

import com.controllers.Parse;
import com.entity.Device;
import com.notification.HealthChecker;
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
@ComponentScan(basePackages = "com")
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

        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        //   startByFile(xml);

        testBean.add();
        test1.testSave();
    }

    public void startByFile(File file) {
        List<Device> device = Parse.getDevice(file);
        device.forEach(device1 -> monitor.addDeviceOnExecute(device1));
        monitor.beginExecute();
        healthChecker.startCheck();
        monitor.addLoad();
    }



}