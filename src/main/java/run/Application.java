package run;

import controllers.Monitor;
import controllers.Parse;
import criteria.Task;
import enums.Vendor;
import main.Device;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");

        List<Device> device = Parse.getDevice(xml);
        List<Task> tasks = new ArrayList<>();
        Monitor monitor = new Monitor();
        monitor.beginExecute();
        monitor.addDeviceOnExecute(device.get(0), Vendor.valueOf(device.get(0).getVendor().toUpperCase()).getCpu1MinuteTask());
    //    SpringApplication.run(Application.class, args);
        Thread.sleep(15000);
        monitor.addDeviceOnExecute(device.get(1), Vendor.valueOf(device.get(1).getVendor().toUpperCase()).getTestTask());


    }
}