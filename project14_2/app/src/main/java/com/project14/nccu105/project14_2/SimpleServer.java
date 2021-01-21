package com.project14.nccu105.project14_2;

import android.widget.Toast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleServer {

    public static ArrayList<Socket> socketList = new ArrayList<Socket>();
    public static String content="";


    public static void main(String[] args) throws IOException {
        //建立ServerSocket
        ServerSocket ss = new ServerSocket(3000);

        //不断接收此端口的socket，并存储到socketList中
        //并且为每一个socket开启一个线程，用于接收信息
        while (true) {

            Socket s = ss.accept();
            System.out.println("connect success!");
            socketList.add(s);

            new Thread(new ServerThead(s)).start();
        }
    }

}

