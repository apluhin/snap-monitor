package com.test.controllers;

import com.test.repository.CpuRepository;
import com.test.repository.DeviceRepository;
import com.test.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

@Component
public class FactoryRepository {

    static FactoryRepository Factory = new FactoryRepository();
    static CpuRepository cpuRepository;
    static RamRepository ramRepository;
    static DeviceRepository deviceRepository;
    @Autowired
    Add add;

    public static CpuRepository getCpuRepository() {
        return cpuRepository;
    }

    public static RamRepository getRamRepository() {
        return ramRepository;
    }

    public static DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    @Component
    public class Add {

        @Autowired
        CpuRepository cpuRepository;

        @Autowired
        RamRepository ramRepository;

        @Autowired
        DeviceRepository deviceRepository;

        @PostConstruct
        public void setRepository() {
            Field[] fields = this.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field from = fields[i];
                    Field to = FactoryRepository.class.getDeclaredField(from.getName());
                    to.setAccessible(true);
                    to.set(Factory, from.get(this));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    System.out.println("");
                }
            }
        }


    }


}
