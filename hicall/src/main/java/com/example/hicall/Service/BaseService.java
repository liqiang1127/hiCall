package com.example.hicall.Service;

import com.example.hicall.bean.Device;
import com.example.hicall.utils.HiCallConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.Map;

@Service
public class BaseService {
    @Autowired
    protected ServletContext servletContext;

    protected Map<String, Device> devicesList;

    protected String ret = HiCallConstant.SUCCESS;

    protected Map<String, Device> getDevicesList(){
        return (Map<String, Device>)servletContext.getAttribute(HiCallConstant.devicesListKey);
    }
}
