package com.test.repository;

import com.test.entity.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {

    List<CpuEntity> findByAddress(String address);

    @Query(value = "select entity from CpuEntity entity where entity.address like ?1 and entity.timestamp > ?2")
    List<CpuEntity> findByAddressAndTime(String address, Timestamp timestamp);


}
