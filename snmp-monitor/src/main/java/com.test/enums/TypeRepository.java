package com.test.enums;

import com.test.controllers.FactoryRepository;
import com.test.entity.CpuEntity;
import com.test.entity.Device;
import com.test.entity.DeviceEntity;
import com.test.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public enum TypeRepository {


    CpuLoad {
        @Override
        public JpaRepository getRepository() {
            return FactoryRepository.getCpuRepository();
        }

        @Override
        public void saveResult(Object execute, Device key) {
            FactoryRepository.getCpuRepository().save(new CpuEntity(key.getAddress().toString(), Timestamp.valueOf(LocalDateTime.now()), (Integer) execute));
        }
    },
    FreeMem {
        @Override
        public JpaRepository getRepository() {
            return FactoryRepository.getRamRepository();
        }

        @Override
        public void saveResult(Object execute, Device key) {
            FactoryRepository.getRamRepository().save(new RamEntity(key.getAddress().toString(), (Long) execute, Timestamp.valueOf(LocalDateTime.now())));
        }
    },
    sysName {
        @Override
        public JpaRepository getRepository() {
            System.out.println(FactoryRepository.getDeviceRepository());
            return FactoryRepository.getDeviceRepository();
        }

        @Override
        public void saveResult(Object execute, Device key) {
            FactoryRepository.getDeviceRepository().save(new DeviceEntity((String) execute, key.getVendor(), key.getAddress().toString()));
        }
    };

    public abstract JpaRepository getRepository();


    public abstract void saveResult(Object execute, Device key);
}
