package com.example.hicall.controller;

import com.example.hicall.Service.HiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
    @Autowired
    public HiCallService hiCallService;
}
