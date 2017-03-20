package controllers;

import criteria.Task;
import main.Device;
import mib.Command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexey on 03.10.16.
 */
public class Handler {
    private  Map<Device, List<Task>> deviceListMap;
    Map<Device, List<Result>> map;
    Map<Device, List<Command>> tasks;


    public Handler(Map<Device, List<Task>> deviceListMap) {
        this.deviceListMap =  deviceListMap;

    }


    public void addTaskOnDevice(Device device, Task task) {
        if (isConnected(device)) {
            deviceListMap.putIfAbsent(device, new CopyOnWriteArrayList<>());
            deviceListMap.get(device).add(task);
        }
    }

    private boolean isConnected(Device device) {
        try {
            Object execute = device.getTestConnect().execute(device);
            System.out.println(execute);
        } catch (NullPointerException | ClassCastException e) {
            System.out.println("Can't find");
            return false;
        }
        return true;
    }

    public Map<Device, List<Result>> runTasks() {
        map = new ConcurrentHashMap<>();
        for (Map.Entry<Device, List<Task>> deviceListEntry : deviceListMap.entrySet()) {
            List<Task> value = deviceListEntry.getValue();
            Device key = deviceListEntry.getKey();
            value.forEach(s -> {
                try {
                    Object execute = execute(key, s);
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


}
