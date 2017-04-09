package com.test;

import com.test.controllers.Parse;
import com.test.entity.Device;
import com.test.entity.DeviceEntity;
import com.test.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Test1 {

    @Autowired
    DeviceRepository repository;


    public void addDevice(DeviceEntity device) {
        repository.save(device);
    }


    public void testSave() {
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        List<Device> device = Parse.getDevice(xml);
        List<DeviceEntity> collect = device.stream().
                map(s -> new DeviceEntity(s.getName(), s.getVendor(), s.getAddress().toString())).
                collect(Collectors.toList());
        repository.save(collect);
        repository.findAll().forEach(System.out::println);

    }

}
