package com.test;

import com.test.criteria.Task;
import com.test.entity.Device;
import com.test.enums.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Controller
public class Monitor {


    private Map<Device, List<Task>> mapOfDevice = new ConcurrentHashMap<>();
    @Autowired
    private Handler handler;

    public Monitor(Map<Device, List<Task>> tasks) {
        this.mapOfDevice = tasks;
        handler = new Handler(tasks);
    }

    public Monitor() {
    }

    public void addDeviceOnExecute(Device device, Task task) {
        mapOfDevice.putIfAbsent(device, new CopyOnWriteArrayList<>());
        mapOfDevice.get(device).add(task);
    }

    public void beginExecute() {
        new Thread(() -> {
            while (true) {
                try {
                    handler.runTasks();
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addLoad() {
        Device next = mapOfDevice.keySet().iterator().next();
        new Thread(() -> {
            while (true) {
                try {
                    Vendor.valueOf(next.getVendor().toUpperCase()).getTestTask().execute(next);
                } catch (Exception e) {

                }
            }
        }).start();
    }





    @PostConstruct
    public void cont() {
        mapOfDevice = handler.getDeviceListMap();
    }


    public List<Device> getListDevices() {
        return mapOfDevice.keySet().stream().collect(Collectors.toList());
    }

    public void addDeviceOnExecute(Device device) {
        for (Device dev : mapOfDevice.keySet()) {
            if (dev.getAddress().toString().equalsIgnoreCase(device.getAddress().toString())) {
                return;
            }
        }
        mapOfDevice.putIfAbsent(device, new CopyOnWriteArrayList<>());
        mapOfDevice.get(device).add(Vendor.valueOf(device.getVendor()).getCpu1MinuteTask());
        mapOfDevice.get(device).add(Vendor.valueOf(device.getVendor()).getFreeMemory());
    }
}
