package com.example.hicall.dao;

import com.example.hicall.bean.Device;
import com.example.hicall.utils.HiCallConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

@Component
public class DevicesListRunner implements ApplicationRunner {
    @Autowired
    ServletContext servletContext;

    private Logger logger = LoggerFactory.getLogger(DevicesListRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Device> devicesList = (HashMap<String, Device>)servletContext.getAttribute(HiCallConstant.devicesListKey);
        if(devicesList == null){
            devicesList = new HashMap<>();
            servletContext.setAttribute(HiCallConstant.devicesListKey,devicesList);
            logger.info("create devicesList successfully");
        }
    }
}
