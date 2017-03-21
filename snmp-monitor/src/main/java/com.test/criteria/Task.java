package com.test.criteria;

import com.test.entity.Device;
import org.springframework.stereotype.Controller;

@Controller
public interface Task {

    Object execute(Device device) throws NullPointerException;

    String getName();

}
