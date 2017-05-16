package com.dto;

import java.sql.Timestamp;

public class RamDto {

    private Long idDevice;
    private String address;
    private String currentRam;
    private Timestamp timestamp;

    public RamDto(Long idDevice, String address, String currentRam, Timestamp timestamp) {
        this.idDevice = idDevice;
        this.address = address;
        this.currentRam = currentRam;
        this.timestamp = timestamp;
    }

    public String getAddress() {
        return address;
    }

    public String getCurrentRam() {
        return currentRam;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
