package com.test.repository;


import com.test.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {


}
