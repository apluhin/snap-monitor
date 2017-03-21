package com.test.repository;

import com.test.entity.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {
}
