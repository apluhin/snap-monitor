package com.test.dto;

import java.sql.Timestamp;

public class RamDto {

    private String address;
    private String currentRam;
    private Timestamp timestamp;

    public RamDto(String address, String currentRam, Timestamp timestamp) {
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
