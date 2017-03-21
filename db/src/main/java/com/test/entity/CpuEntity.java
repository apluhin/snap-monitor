package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cpu")
public class CpuEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private LocalDateTime dateTime;
    private int load;

    public CpuEntity(String address, LocalDateTime dateTime, int load) {
        this.address = address;
        this.dateTime = dateTime;
        this.load = load;
    }

    public CpuEntity() {

    }
}
