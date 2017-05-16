package com.service;

import com.Monitor;
import com.controllers.Parse;
import com.dto.DeviceDto;
import com.entity.CpuEntity;
import com.entity.Device;
import com.entity.DeviceEntity;
import com.entity.RamEntity;
import com.enums.TypeRepository;
import com.repository.CpuRepository;
import com.repository.DeviceRepository;
import com.repository.RamRepository;
import com.snmp.SnmpDevice;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
        List<Object> ramByEveryId = ramRepository.getRamByEveryId();
        for (int i = 0; i < ramByEveryId.size(); i++) {
            //System.out.println(((Object[]) ramByEveryAddress.get(i)).length);
        }
        ConcurrentMap<BigInteger, BigInteger> collect = ramByEveryId.stream().map(o -> (Object[]) o)
                .filter(objects -> objects[1] != null)
                .collect(Collectors.toConcurrentMap(d -> (BigInteger) (d[0]), d -> (BigInteger) d[1]));
        List<DeviceEntity> all = deviceRepository.findAll();
        return all.
                stream().
                map(deviceEntity -> new DeviceDto(deviceEntity.getId(), deviceEntity.getName(), deviceEntity.getVendor(), deviceEntity.getAddress(), collect.getOrDefault(new BigInteger(String.valueOf(deviceEntity.getId())), null), deviceEntity.isOnline())).
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

    public List<CpuEntity> getCpuMonitoring(Long id) {
        List<CpuEntity> byAddress = ((CpuRepository) (TypeRepository.CpuLoad.getRepository())).findByIdDevice(id);
        return byAddress;
    }

    public List<RamEntity> getRomMonitoring(Long id) {
        DeviceEntity byAddress1 = deviceRepository.findById(id);
        List<RamEntity> byAddress = ((RamRepository) (TypeRepository.FreeMem.getRepository())).findByIdDevice(byAddress1.getId());
        return byAddress;
    }

    public List<CpuEntity> getCpuByInterval(Long id, String from, String to) {
        Timestamp timestampFrom = Timestamp.valueOf(from);
        Timestamp timestampTo = Timestamp.valueOf(to);
        List<CpuEntity> byAddressAndTwoTimestamp = ((CpuRepository) (TypeRepository.CpuLoad.getRepository())).
                findByIdDeviceAndTwoTimestamp(id, timestampFrom, timestampTo);
        return byAddressAndTwoTimestamp;
    }

    public void addDevices(MultipartFile file) throws IOException {
        File file1 = new File("in.xml");

        saveFile(file, file1);
        System.out.println(file1.getAbsoluteFile());
        List<Device> device = Parse.getDevice(file1);
        device.forEach(monitor::addDeviceOnExecute);
    }

    public void saveFile(MultipartFile file, File dest) throws IOException {
        dest.createNewFile();
        FileWriter writer = new FileWriter(dest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        while (reader.ready()) {
            writer.append(reader.readLine());
            writer.flush();
        }
        writer.close();
        reader.close();

    }
}
