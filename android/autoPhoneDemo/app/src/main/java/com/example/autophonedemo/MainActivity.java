package com.example.autophonedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.autophonedemo.thread.CommandThread;
import com.example.autophonedemo.thread.ConnectThread;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //http请求队列
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        //创建连接线程
        ConnectThread connectThread = new ConnectThread(queue);
        Thread thread = new Thread(connectThread);
        thread.start();

        //创建获取命令线程
        CommandThread commandThread = new CommandThread(queue);
        Thread thread1 = new Thread(commandThread);
        thread1.start();
    }
}
