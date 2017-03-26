package com.test.controller;


import com.test.dto.DeviceDto;
import com.test.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(params = "action=add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addDevice(HttpServletRequest request) throws IOException {
        System.out.println(request.getReader().lines().collect(Collectors.toList()));
        return false;
    }

}
