package com.db.my_spring;

import lombok.Data;
import lombok.SneakyThrows;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Data
public class BenchmarkToggle implements BenchmarkToggleMBean {
    private boolean enabled;

    @SneakyThrows
    public BenchmarkToggle() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(this,
                new ObjectName("deutsche mbeans", "name", "benchmark"));
    }
}
