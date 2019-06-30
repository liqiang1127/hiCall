package com.example.hicall.bean;

import com.example.hicall.utils.HiCallUitls;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * http响应父类
 */
@Component
public class HiCallResponse {
    //错误编码
    private String errorNo;
    //提示信息
    private String message;
    //响应时间
    private String time;
    //其他信息
    private Map<String,Object> info;

    public HiCallResponse() {
        this.errorNo ="0";
        this.message = "successful";
        this.time = HiCallUitls.getDateString();
        this.info = new HashMap<>();
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
