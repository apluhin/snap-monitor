package com.repository;

import com.entity.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {

    List<CpuEntity> findByIdDevice(Long idDevice);

    @Query(value = "select entity from CpuEntity entity where entity.idDevice = ?1 and entity.timestamp > ?2")
    List<CpuEntity> findByIdDeviceAndTime(Long idDevice, Timestamp timestamp);

    @Query(value = "select entity from CpuEntity entity where entity.idDevice = ?1 and entity.timestamp > ?2 and entity.timestamp < ?3")
    List<CpuEntity> findByIdDeviceAndTwoTimestamp(Long idDevice, Timestamp from, Timestamp to);


}
