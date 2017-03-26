package com.test.service;

import com.test.Monitor;
import com.test.dto.DeviceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {


    private final Monitor monitor;

    @Autowired
    public DeviceService(Monitor monitor) {
        this.monitor = monitor;
    }

    public List<DeviceDto> getListDevices() {
        return monitor.getListDevices().
                stream().
                map(DeviceDto::new).
                collect(Collectors.toList());
    }

    public boolean addDevice(String ipAddress, String vendor) {
        return false;
    }
}
