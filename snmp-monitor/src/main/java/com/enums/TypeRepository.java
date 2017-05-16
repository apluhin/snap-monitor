package com.enums;

import com.controllers.FactoryRepository;
import com.entity.CpuEntity;
import com.entity.Device;
import com.entity.DeviceEntity;
import com.entity.RamEntity;
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
            Long id = FactoryRepository.getDeviceRepository().findByAddress(key.getAddress()).getId();

            if (execute == null) {
                FactoryRepository.getCpuRepository().save(new CpuEntity(id, Timestamp.valueOf(LocalDateTime.now()), null));
                return;
            }
            FactoryRepository.getCpuRepository().save(new CpuEntity(id, Timestamp.valueOf(LocalDateTime.now()), (Integer) execute));
        }
    },
    FreeMem {
        @Override
        public JpaRepository getRepository() {
            return FactoryRepository.getRamRepository();
        }

        @Override
        public void saveResult(Object execute, Device key) {
            Long id = FactoryRepository.getDeviceRepository().findByAddress(key.getAddress()).getId();

            if (execute == null) {
                FactoryRepository.getRamRepository().save(new RamEntity(id, null, Timestamp.valueOf(LocalDateTime.now())));
                return;
            }
            FactoryRepository.getRamRepository().save(new RamEntity(id, (Long) execute, Timestamp.valueOf(LocalDateTime.now())));
        }
    },
    sysName {
        @Override
        public JpaRepository getRepository() {
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
