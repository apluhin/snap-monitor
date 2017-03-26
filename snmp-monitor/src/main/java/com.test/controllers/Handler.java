package com.test.controllers;

import com.test.criteria.Task;
import com.test.entity.Device;
import com.test.enums.TypeRepository;
import com.test.enums.Vendor;
import com.test.mib.Command;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexey on 03.10.16.
 */
@Controller
public class Handler {


    Map<Device, List<Result>> map;
    Map<Device, List<Command>> tasks;
    private Map<Device, List<Task>> deviceListMap;
    private Map<Device, Boolean> mapStatus = new ConcurrentHashMap<>();


    public Handler(Map<Device, List<Task>> deviceListMap) {
        this.deviceListMap =  deviceListMap;
    }

    public Handler() {
        this.deviceListMap = new ConcurrentHashMap<>();
    }


    public void addTaskOnDevice(Device device, Task task) {
        if (isConnected(device)) {
            deviceListMap.putIfAbsent(device, new CopyOnWriteArrayList<>());
            deviceListMap.get(device).add(task);
        }
    }

    private boolean isConnected(Device device) {
        try {
            Object execute = Vendor.valueOf(device.getVendor().toUpperCase()).getTestTask().execute(device);
            device.setName(execute.toString());
            System.out.println(execute);
            mapStatus.put(device, true);
        } catch (NullPointerException | ClassCastException e) {
            if (mapStatus.get(device) == null) {
                deviceListMap.remove(device);
            }
            System.out.println("Can't find");
            return false;
        }
        return true;
    }

    public Map<Device, List<Result>> runTasks() {
        map = new ConcurrentHashMap<>();
        for (Map.Entry<Device, List<Task>> deviceListEntry : deviceListMap.entrySet()) {
            isConnected(deviceListEntry.getKey());
            if (mapStatus.get(deviceListEntry.getKey()) == null) {
                break;
            }
            List<Task> value = deviceListEntry.getValue();
            Device key = deviceListEntry.getKey();
            value.forEach(s -> {
                try {
                    Object execute = execute(key, s);
                    TypeRepository.valueOf(s.getName()).saveResult(execute, key);
                    putIn(key, s, execute);
                } catch (IOException | NullPointerException e) {
                    System.out.println("Can't connect to, device now isn't unreacheble " + key.getAddress());
                }
            });
        }
        return map;
    }

    private void putIn(Device key, Task s, Object result) {
        map.putIfAbsent(key, new CopyOnWriteArrayList<>());
        map.get(key).add(new Result(s, result));
    }

    private Object execute(Device device, Task s) throws IOException {
        return s.execute(device);
    }

    public Map<Device, List<Task>> getDeviceListMap() {
        return deviceListMap;
    }
}
