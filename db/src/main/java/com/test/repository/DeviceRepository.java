package com.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.test.entity.DeviceEntity;


public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

}
