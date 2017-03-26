package com.test.service;

import com.test.Monitor;
import com.test.dto.DeviceDto;
import com.test.entity.CpuEntity;
import com.test.entity.Device;
import com.test.enums.TypeRepository;
import com.test.repository.CpuRepository;
import com.test.snmp.SnmpDevice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceService {


    private final Monitor monitor;

    @Autowired
    public DeviceService(Monitor monitor) {
        this.monitor = monitor;
    }

    public List<DeviceDto> getListDevices() {
        return monitor.getListDevices().
                stream().
                map(DeviceDto::new).
                collect(Collectors.toList());
    }

    public String addDevice(String mapOfJson) {
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

    public List<CpuEntity> getCpuMonitoring(String address) {
        List all = TypeRepository.CpuLoad.getRepository().findAll();
        System.out.println(all);
        List<CpuEntity> byAddress = ((CpuRepository) (TypeRepository.CpuLoad.getRepository())).findByAddress("/" + address);
        return byAddress;
    }
}
