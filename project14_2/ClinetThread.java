package com.project14.nccu105.project14_2;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 项目名称：SocketTestTwo
 * 创建人：Double2号
 * 创建时间：2016.11.20 9:16
 * 修改备注：
 */
public class ClinetThread implements Runnable {
    private Socket mSocket;
    private BufferedReader mBufferedReader = null;
    private OutputStream mOutputStream = null;
    private Handler mHandler;

    public Handler revHandler;

    public ClinetThread(Handler handler) {
        mHandler = handler;
    }
    InetAddress ip;
    @Override
    public void run() {
        try {
//            ip = InetAddress.getLocalHost();
//            String ip2= ip.getAddress().toString();
            mSocket = new Socket("192.168.1.102", 3000);
            Log.d("xjj","connect success");
            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOutputStream = mSocket.getOutputStream();

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        String content = null;
                        while ((content = mBufferedReader.readLine()) != null) {
                            Log.d("xjj",content);
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = content;
                            mHandler.sendMessage(msg);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }.start();

            //由于子线程中没有默认初始化Looper，要在子线程中创建Handler，需要自己写
            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        try {
                            mOutputStream.write((msg.obj.toString() + "\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();





        } catch (IOException e) {
            e.printStackTrace();
            Log.d("xjj","");
        }
    }
}