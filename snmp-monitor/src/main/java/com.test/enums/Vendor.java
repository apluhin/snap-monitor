package com.test.enums;

import com.test.criteria.CpuLoad;
import com.test.criteria.FreeMem;
import com.test.criteria.Name;
import com.test.criteria.Task;
import com.test.mib.Command;
import org.snmp4j.PDU;

public enum Vendor {
    CISCO {
        @Override
        public Task getTestTask() {
            Command command = new Command("cisco", "Device", ".1.3.6.1.2.1.1.5", PDU.getTypeFromString("GETNEXT"));
            return new Name(command);
        }

        @Override
        public Task getCpu1MinuteTask() {
            //cisco, cpu1Minute, .1.3.6.1.4.1.9.2.1.57.0, GET
            Command command = new Command("cisco", "cpu1Minute", ".1.3.6.1.4.1.9.2.1.57.0", PDU.getTypeFromString("GET"));
            return new CpuLoad(command);
        }
//cisco, freeRam, , GET oid_ciscoMemoryPoolFree=".1.3.6.1.4.1.9.9.48.1.1.1.6.2"

        @Override
        public Task getFreeMemory() {
            Command command = new Command("cisco", "freeMem", ".1.3.6.1.4.1.9.2.1.8.0", PDU.getTypeFromString("GET"));
            return new FreeMem(command);
        }
    },
    HUAWEI {
        @Override
        public Task getTestTask() {
            return null;
        }

        @Override
        public Task getCpu1MinuteTask() {
            return null;
        }

        @Override
        public Task getFreeMemory() {
            return null;
        }
    };



    public abstract Task getTestTask();

    public abstract Task getCpu1MinuteTask();

    public abstract Task getFreeMemory();

}
