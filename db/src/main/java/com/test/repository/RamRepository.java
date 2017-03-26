package com.test.repository;

import com.test.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamRepository extends JpaRepository<RamEntity, Long> {
}
