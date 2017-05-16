package com.repository;

import com.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByIdDevice(Long idDevice);

    @Query(value = "SELECT id_device, current_ram FROM ram WHERE timestamp in (SELECT max(timestamp) from ram GROUP BY id_device)", nativeQuery = true)
    List<Object> getRamByEveryId();

}
