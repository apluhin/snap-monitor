package com.test.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Cpu")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CpuEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private Timestamp timestamp;
    private Integer load;

    public CpuEntity(String address, Timestamp timestamp, Integer load) {
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

    public Integer getLoad() {
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
