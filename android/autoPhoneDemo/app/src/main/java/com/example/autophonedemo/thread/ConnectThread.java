package com.example.autophonedemo.thread;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ConnectThread implements Runnable{
    private boolean isRegister;
    private RequestQueue mRequestQueue;
    private String registerUrl ="http://183.129.202.170:5000/download";
    private String keepUrl = "xxxx";

    public ConnectThread(RequestQueue mRequestQueue){
        this.mRequestQueue = mRequestQueue;
        isRegister = false;
    }
    @Override
    public void run() {
        while(true){
            if(!isRegister){
                //没有注册就去注册
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,registerUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println(response.toString());
                                if("1".equals(response.toString())){
                                    //注册成功
                                    isRegister = true;
                                }else{
                                    //注册失败
                                    isRegister = false;
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //出现网络问题
                        isRegister = false;
                    }
                });
                jsonObjectRequest.setTag("register");
                mRequestQueue.add(jsonObjectRequest);
            }else{
                //已经注册过了 保持心跳
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,keepUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if("1".equals(response.toString())){
                                    //延长时长成功
                                    isRegister = true;
                                }else{
                                    //心跳失败 说明断线了 需要重新登录
                                    isRegister = false;
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //出现异常 也认为断线了 需要重新登录
                        isRegister = false;
                    }
                });
                jsonObjectRequest.setTag("keep");
                mRequestQueue.add(jsonObjectRequest);
            }
            try {
                //线程挂起2s
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
