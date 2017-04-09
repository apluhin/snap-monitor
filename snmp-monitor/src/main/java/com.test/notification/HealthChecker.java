package com.test.notification;

import com.test.entity.CpuEntity;
import com.test.entity.DeviceEntity;
import com.test.repository.CpuRepository;
import com.test.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HealthChecker {

    @Autowired
    CpuRepository cpuRepository;
    @Autowired
    DeviceRepository deviceRepository;
    private volatile int minuteWaiting = 1;

    public void startCheck() {
        new Thread(() -> {
            while (true) {
                try {
                    checkOnline();
                    Thread.sleep(15000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkOnline() {

        List<DeviceEntity> all = deviceRepository.findAll();
        for (DeviceEntity device : all) {
            LocalDateTime dateTime = LocalDateTime.now().minusMinutes(minuteWaiting);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            List<CpuEntity> byAddressAndTime = cpuRepository.
                    findByAddressAndTime(device.getAddress(), timestamp);

            if (byAddressAndTime.size() == 0) {
                System.out.println("Error device doest'n");
            } else {
                //      System.out.println(byAddressAndTime.size() + "   size");
            }
        }
    }


    public void setTime(Integer time) {
        if (time <= 0) {
            return;
        }
        this.minuteWaiting = time;
    }
}
