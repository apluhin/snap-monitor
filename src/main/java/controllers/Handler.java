package controllers;

import criteria.Critirea;
import main.Device;

import java.io.IOException;
import java.util.*;

/**
 * Created by alexey on 03.10.16.
 */
public class Handler {
    private final Map<Device, List<Critirea>> deviceListMap;
    Map<Device, List<Result>> map;

    public Handler(Map<Device, List<Critirea>> deviceListMap) {
        this.deviceListMap = deviceListMap;
    }

    public Handler() {
        this.deviceListMap = new HashMap<>();
    }

    public void addCriteria(Device device, Critirea critirea) {
        deviceListMap.putIfAbsent(device, new ArrayList<>());
        deviceListMap.get(device).add(critirea);
    }

    public Map<Device, List<Result>> executeAll() {
        map = new HashMap<>();
        for (Map.Entry<Device, List<Critirea>> deviceListEntry : deviceListMap.entrySet()) {
            List<Critirea> value = deviceListEntry.getValue();
            Device key = deviceListEntry.getKey();
            value.forEach(s -> {
                try {
                    Object execute = execute(key, s);
                    putIn(key, s, execute);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return map;
    }

    private void putIn(Device key, Critirea s, Object result) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Result(s, result));
    }

    private Object execute(Device device, Critirea s) throws IOException {
        return s.execute(device);
    }


}
