package com.test.repository;

import com.test.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RamRepository extends JpaRepository<RamEntity, Long> {

    public List<RamEntity> findByAddress(String address);

}
