package com.example.hicall.controller;

import com.example.hicall.bean.Device;
import com.example.hicall.bean.HiCallResponse;
import com.example.hicall.utils.HiCallConstant;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableAutoConfiguration
@RestController
public class HiCallController extends BaseController{

    @RequestMapping("/login")
    public HiCallResponse login(@RequestBody Device device){
        HiCallResponse res = new HiCallResponse();
        String serviceInfo = hiCallService.login(device);
        if(HiCallConstant.SUCCESS.equals(serviceInfo))
            return res;
        else{
            res.setErrorNo(String.valueOf(-1));
            res.setMessage(serviceInfo);
        }
        return res;
    }

    @RequestMapping("/getDeviceList")
    public HiCallResponse getDeviceList(){
        HiCallResponse res = new HiCallResponse();
        Map<String,Device> devicesList = hiCallService.devicesList();
        res.getInfo().put("count",String.valueOf(devicesList.size()));
        res.getInfo().put("devices",devicesList);
        return res;
    }


}
