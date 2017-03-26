package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "cpu")
public class CpuEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private Timestamp timestamp;
    private int load;

    public CpuEntity(String address, Timestamp timestamp, int load) {
        this.address = address;
        this.timestamp = timestamp;
        this.load = load;
    }

    public CpuEntity() {

    }

    public String getAddress() {
        return address;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getLoad() {
        return load;
    }

    @Override
    public String toString() {
        return "CpuEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                ", load=" + load +
                '}';
    }
}
