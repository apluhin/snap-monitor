package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ram")
public class RamEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private int currentRam;

    public RamEntity() {
    }

    public RamEntity(String address, int currentRam) {
        this.address = address;
        this.currentRam = currentRam;
    }

    @Override
    public String toString() {
        return "RamEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", currentRam=" + currentRam +
                '}';
    }
}
