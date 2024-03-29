package com.service;

import com.notification.HealthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    HealthChecker healthChecker;

    public boolean setAwaiting(Integer awaiting) {
        healthChecker.setTime(awaiting);
        return true;
    }
}
