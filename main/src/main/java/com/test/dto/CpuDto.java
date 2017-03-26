package com.test.dto;

import com.test.entity.CpuEntity;

public class CpuDto {
    private final String address;
    private final String load;
    private final long timestamp;

    public CpuDto(CpuEntity cpuEntity) {
        this.address = cpuEntity.getAddress();
        this.load = String.valueOf(cpuEntity.getLoad());
        this.timestamp = cpuEntity.getTimestamp().getTime();
    }
}
