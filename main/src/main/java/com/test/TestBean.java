package com.test;

import com.test.controllers.Parse;
import com.test.entity.CpuEntity;
import com.test.entity.Device;
import com.test.repository.CpuRepository;
import com.test.repository.DeviceRepository;
import com.test.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestBean {

    @Autowired
    CpuRepository repository;

    @Autowired
    DeviceService service;

    @Autowired
    DeviceRepository deviceRepository;

    public void add() {
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");
        List<Device> device = Parse.getDevice(xml);
        deviceRepository.save(service.transform(device));
        List<CpuEntity> e = new ArrayList<>();
        for (long i = 10000; i > 0; i--) {
            e.add(new CpuEntity("/192.168.0.1", Timestamp.valueOf(LocalDateTime.now().minusMinutes(i)), (int) (50 - Math.random() * 5)));
            e.add(new CpuEntity("/192.168.0.2", Timestamp.valueOf(LocalDateTime.now().minusMinutes(i)), (int) (50 - Math.random() * 10)));
        }
        repository.save(e);
    }


}
