package com.example.autophonedemo.thread;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class CommandThread implements Runnable {
    private RequestQueue mRequestQueue;
    private String commandUrl ="https://mcs.snssdk.com/v1/list";

    public CommandThread(RequestQueue mRequestQueue){
        this.mRequestQueue = mRequestQueue;
    }
    @Override
    public void run() {
        while(true){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,commandUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            jsonObjectRequest.setTag("register");
            mRequestQueue.add(jsonObjectRequest);
            try {
                //线程挂起2s
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
