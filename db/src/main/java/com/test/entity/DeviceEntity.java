package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Device")
public class DeviceEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String vendor;
    private String address;

    public DeviceEntity() {
    }

    public DeviceEntity(String name, String vendor, String address) {
        this.name = name;
        this.vendor = vendor;
        this.address = address;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
