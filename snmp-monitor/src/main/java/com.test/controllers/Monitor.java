package com.test.controllers;

import com.test.criteria.Task;
import com.test.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class Monitor {




    private  Map<Device, List<Task>> mapOfDevice;
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
                handler.runTasks();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @PostConstruct
    public void cont() {
        mapOfDevice = handler.getDeviceListMap();
    }


}
