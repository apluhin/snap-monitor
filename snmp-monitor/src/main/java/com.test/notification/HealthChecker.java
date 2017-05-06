package com.test.notification;

import com.test.entity.CpuEntity;
import com.test.entity.DeviceEntity;
import com.test.notification.email.EmailSender;
import com.test.repository.CpuRepository;
import com.test.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HealthChecker {

    @Autowired
    CpuRepository cpuRepository;
    @Autowired
    DeviceRepository deviceRepository;
    private volatile int minuteWaiting = 1;
    private Map<String, Boolean> status = new HashMap<>();

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
        EmailSender emailSender = new EmailSender();

        List<DeviceEntity> all = deviceRepository.findAll();
        List<DeviceEntity> notify = new ArrayList<>();

        for (DeviceEntity device : all) {
            LocalDateTime dateTime = LocalDateTime.now().minusMinutes(minuteWaiting);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            List<CpuEntity> byAddressAndTime = cpuRepository.
                    findByAddressAndTime(device.getAddress(), timestamp);

            if (!foundNotNull(byAddressAndTime)) {
                if (status.get(device.getAddress())) {
                    System.out.println("Error, device unavailible");
                    deviceRepository.updateOfflineStatus(device.getAddress());
                    notify.add(device);
                }
                status.putIfAbsent(device.getAddress(), false);
                status.put(device.getAddress(), false);
            } else {
                //      System.out.println(byAddressAndTime.size() + "   size");
                if (!device.isOnline()) {
                    deviceRepository.updateOnlineStatus(device.getAddress());
                    status.putIfAbsent(device.getAddress(), true);
                    status.put(device.getAddress(), true);
                }
            }
        }
        if (notify.size() != 0) {
            try {
                emailSender.sendNotification(notify);
            } catch (IOException e) {
                System.out.println("Error during send notify");
            }
        }
    }

    private boolean foundNotNull(List<CpuEntity> byAddressAndTime) {
        for (CpuEntity ent : byAddressAndTime) {
            if (ent.getLoad() != null) {
                return true;
            }
        }
        return false;
    }


    public void setTime(Integer time) {
        if (time <= 0) {
            return;
        }
        this.minuteWaiting = time;
    }
}
