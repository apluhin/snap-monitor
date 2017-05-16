package com.dto;

import lombok.Getter;

@Getter
public class CpuDto {
    private final Long idDevice;
    private final String address;
    private final String load;
    private final long timestamp;

    public CpuDto(Long idDevice, String address, String load, long timestamp) {
        this.idDevice = idDevice;
        this.address = address;
        this.load = load;
        this.timestamp = timestamp;
    }
}
