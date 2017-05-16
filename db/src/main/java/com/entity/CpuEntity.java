package com.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Cpu")
@JsonInclude(JsonInclude.Include.ALWAYS)
@Getter
@ToString
public class CpuEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Long idDevice;
    private Timestamp timestamp;
    private Integer load;

    public CpuEntity(Long idDevice, Timestamp timestamp, Integer load) {
        this.idDevice = idDevice;
        this.timestamp = timestamp;
        this.load = load;
    }

    public CpuEntity() {

    }


}
