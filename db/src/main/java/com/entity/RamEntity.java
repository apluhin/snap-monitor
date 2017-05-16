package com.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "Ram")
@Getter
@ToString
public class RamEntity {

    @Id
    @GeneratedValue
    private BigInteger id;
    private Long idDevice;
    private Timestamp timestamp;
    private Long currentRam;

    public RamEntity() {
    }

    public RamEntity(Long id_address, Long currentRam, Timestamp timestamp) {
        this.idDevice = id_address;
        this.currentRam = currentRam;
        this.timestamp = timestamp;
    }


}
