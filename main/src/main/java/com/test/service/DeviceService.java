package com.test.service;

import com.test.Monitor;
import com.test.dto.DeviceDto;
import com.test.entity.CpuEntity;
import com.test.entity.Device;
import com.test.entity.DeviceEntity;
import com.test.entity.RamEntity;
import com.test.enums.TypeRepository;
import com.test.repository.CpuRepository;
import com.test.repository.DeviceRepository;
import com.test.repository.RamRepository;
import com.test.snmp.SnmpDevice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final Monitor monitor;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RamRepository ramRepository;

    @Autowired
    public DeviceService(Monitor monitor) {
        this.monitor = monitor;
    }

    @Transactional
    public List<DeviceDto> getListDevices() {
        List<Object> ramByEveryAddress = ramRepository.getRamByEveryAddress();
        for (int i = 0; i < ramByEveryAddress.size(); i++) {
            System.out.println(((Object[]) ramByEveryAddress.get(i)).length);
        }
        ConcurrentMap<String, BigInteger> collect = ramByEveryAddress.stream().map(o -> (Object[]) o)
                .filter(objects -> objects[1] != null)
                .collect(Collectors.toConcurrentMap(d -> (String) (d[0]), d -> (BigInteger) d[1]));
        List<DeviceEntity> all = deviceRepository.findAll();
        return all.
                stream().
                map(deviceEntity -> new DeviceDto(deviceEntity.getName(), deviceEntity.getVendor(), deviceEntity.getAddress(), collect.getOrDefault(deviceEntity.getAddress(), null), deviceEntity.isOnline())).
                collect(Collectors.toList());


    }

    public String addDevice(String mapOfJson) {
        System.out.println(mapOfJson);
        JSONObject object = new JSONObject(mapOfJson);
        object = object.getJSONObject("device");
        Map<String, String> map = new HashMap<>();
        String address = object.getString("address");
        String vendor = object.getString("vendor");
        object = object.getJSONObject("snmp");
        map.put("version", object.getString("version"));
        map.put("username", object.getString("username"));
        map.put("typeHash", object.getJSONObject("hash").getString("typeHash"));
        map.put("hashPassword", object.getJSONObject("hash").getString("hashPassword"));
        map.put("typeEncrypt", object.getJSONObject("encryption").getString("typeEncrypt"));
        map.put("encryptionPassword", object.getJSONObject("encryption").getString("encryptionPassword"));
        SnmpDevice snmpDevice = new SnmpDevice(map);
        Device device = new Device(vendor.toUpperCase(), address, null, snmpDevice);
        monitor.addDeviceOnExecute(device);
        return "Устройство добавлено";
    }

    public List<DeviceEntity> transform(List<Device> list) {
        return list.stream().
                map(device -> new DeviceEntity(device.getName(), device.getVendor(), device.getAddress().toString())).
                collect(Collectors.toList());
    }

    public List<CpuEntity> getCpuMonitoring(String address) {
        List<CpuEntity> byAddress = ((CpuRepository) (TypeRepository.CpuLoad.getRepository())).findByAddress(address);
        return byAddress;
    }

    public List<RamEntity> getRamMonitoring(String address) {
        List<RamEntity> byAddress = ((RamRepository) (TypeRepository.FreeMem.getRepository())).findByAddress(address);
        return byAddress;
    }

    public List<CpuEntity> getCpuByInterval(String address, String from, String to) {
        Timestamp timestampFrom = Timestamp.valueOf(from);
        Timestamp timestampTo = Timestamp.valueOf(to);
        List<CpuEntity> byAddressAndTwoTimestamp = ((CpuRepository) (TypeRepository.CpuLoad.getRepository())).
                findByAddressAndTwoTimestamp(address, timestampFrom, timestampTo);
        return byAddressAndTwoTimestamp;
    }
}
