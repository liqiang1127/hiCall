package com.example.hicall.Service;

import com.example.hicall.bean.Device;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class HiCallService extends BaseService{

    public String login(Device device){
        devicesList = this.getDevicesList();
        try{
            devicesList.put(device.getUser(), device);
        } catch (Exception e){
            ret = e.getMessage();
        }
        return ret;
    }

    public Map<String,Device> devicesList(){
        return this.getDevicesList();
    }
}
