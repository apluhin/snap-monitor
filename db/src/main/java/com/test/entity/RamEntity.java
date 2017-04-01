package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Ram")
public class RamEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private Timestamp timestamp;
    private Long currentRam;

    public RamEntity() {
    }

    public RamEntity(String address, Long currentRam, Timestamp timestamp) {
        this.address = address;
        this.currentRam = currentRam;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Long getCurrentRam() {
        return currentRam;
    }

    @Override
    public String toString() {
        return "RamEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                ", currentRam=" + currentRam +
                '}';
    }
}
