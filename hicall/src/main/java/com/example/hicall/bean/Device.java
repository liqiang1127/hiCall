package com.example.hicall.bean;

import org.springframework.stereotype.Component;

@Component
public class Device {
    private String version;
    private String user;
//    private String appname;
//    private String model;
//    private int ltersrq;//速度

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public String getAppname() {
//        return appname;
//    }
//
//    public void setAppname(String appname) {
//        this.appname = appname;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public int getLtersrq() {
//        return ltersrq;
//    }
//
//    public void setLtersrq(int ltersrq) {
//        this.ltersrq = ltersrq;
//    }
}
