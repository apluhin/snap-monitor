package com.criteria;

import com.entity.Device;
import org.springframework.stereotype.Controller;

@Controller
public interface Task {

    Object execute(Device device) throws NullPointerException;

    String getName();

}
