package controllers;

import criteria.Task;
import main.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class Monitor {

    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    private final Map<Device, List<Task>> mapOfDevice;

    private Handler handler;

    public Monitor(Map<Device, List<Task>> tasks) {
        this.mapOfDevice = tasks;
        handler = new Handler(tasks);
    }

    public Monitor() {
        mapOfDevice = new ConcurrentHashMap<>();
        handler = new Handler(mapOfDevice);
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


}
