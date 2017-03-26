package com.test.repository;

import com.test.entity.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {

    List<CpuEntity> findByAddress(String address);

}
