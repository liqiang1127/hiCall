package com.example.hicall.controller;

import com.example.hicall.bean.HiCallResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class HelloWorldController extends BaseController{
    @RequestMapping("/")
    public HiCallResponse hello(){
        return new HiCallResponse();
    }
}
