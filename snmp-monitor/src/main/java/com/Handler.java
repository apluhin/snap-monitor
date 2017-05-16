package com;

import com.controllers.Result;
import com.criteria.Task;
import com.entity.Device;
import com.enums.TypeRepository;
import com.enums.Vendor;
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


    private Map<Device, List<Result>> map;
    private Map<Device, List<Task>> deviceListMap;
    private Map<Device, Boolean> mapStatus = new ConcurrentHashMap<>();


    public Handler(Map<Device, List<Task>> deviceListMap) {
        this.deviceListMap = deviceListMap;
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
        Object execute;
        try {
            execute = Vendor.valueOf(device.getVendor().toUpperCase()).getTestTask().execute(device);
            //device.setName(execute.toString());
            if (mapStatus.get(device) == null) {
                TypeRepository.sysName.saveResult(execute, device);
            }
            mapStatus.put(device, true);
        } catch (Exception e) {
            if (mapStatus.get(device) == null) {
                deviceListMap.remove(device);
            }
            // System.out.println("Can't check connect to " + device.getAddress());
            return false;
        }
        return true;
    }

    public Map<Device, List<Result>> runTasks() {
        map = new ConcurrentHashMap<>();
        for (Map.Entry<Device, List<Task>> deviceListEntry : deviceListMap.entrySet()) {
            isConnected(deviceListEntry.getKey());
            if (mapStatus.get(deviceListEntry.getKey()) == null || !mapStatus.get(deviceListEntry.getKey())) {
                //If now you cant get informatiion from device
                continue;
            }
            List<Task> value = deviceListEntry.getValue();
            Device key = deviceListEntry.getKey();
            value.forEach(s -> {
                try {
                    Object execute = execute(key, s);
                    TypeRepository.valueOf(s.getName()).saveResult(execute, key);
                } catch (Exception e) {
                    //  System.out.println("Can't connect to, device now isn't unreacheble " + key.getAddress());
                    TypeRepository.valueOf(s.getName()).saveResult(null, key);

                }
            });
        }
        return map;
    }

    private Object execute(Device device, Task s) throws IOException {
        return s.execute(device);
    }

    public Map<Device, List<Task>> getDeviceListMap() {
        return deviceListMap;
    }
}
