package com.test.repository;

import com.test.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByAddress(String address);

    @Query(value = "SELECT address, current_ram FROM ram WHERE timestamp in (SELECT max(timestamp) from ram GROUP BY address)", nativeQuery = true)
    List<Object> getRamByEveryAddress();

}
