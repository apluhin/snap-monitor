package com;

import com.entity.CpuEntity;
import com.repository.CpuRepository;
import com.repository.DeviceRepository;
import com.service.DeviceService;
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


        List<CpuEntity> e = new ArrayList<>();
        for (long i = 100000; i > 0; i--) {
            e.add(new CpuEntity(1L, Timestamp.valueOf(LocalDateTime.now().minusMinutes(i + 10)), (int) (50 - Math.random() * 5)));
            e.add(new CpuEntity(2L, Timestamp.valueOf(LocalDateTime.now().minusMinutes(i + 10)), (int) (50 - Math.random() * 10)));
        }
        repository.save(e);
    }

    public void save() {
        File xml = new File(System.getProperty("user.home") + "/reports", "snmp.xml");

    }


}
