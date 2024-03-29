package com.controller;


import com.dto.DeviceDto;
import com.entity.CpuEntity;
import com.entity.RamEntity;
import com.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(params = "action=all", method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceDto> getListDevices() {
        return deviceService.getListDevices();
    }

    @RequestMapping(params = "action=cpu_interval", method = RequestMethod.GET)
    @ResponseBody
    public List<CpuEntity> getListDeviceByInterval(Long id, String from, String to) {
        return deviceService.getCpuByInterval(id, from, to);
    }

    @RequestMapping(params = "action=add", method = RequestMethod.POST)
    @ResponseBody
    public String addDevice(@RequestParam("file") MultipartFile file) throws IOException {
        deviceService.addDevices(file);
        return "Ok";
    }

    @RequestMapping(params = "action=cpu", method = RequestMethod.GET)
    @ResponseBody
    public List<CpuEntity> getCpuMonitoring(Long id) {

        return deviceService.getCpuMonitoring(id);
    }

    @RequestMapping(params = "action=ram", method = RequestMethod.GET)
    @ResponseBody
    public List<RamEntity> getRamMonitoring(Long id) {
        return deviceService.getRomMonitoring(id);
    }




}
