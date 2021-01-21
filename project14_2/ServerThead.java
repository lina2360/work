package com.project14.nccu105.project14_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThead implements Runnable {

    private Socket mSocket = null;
    private BufferedReader mBufferedReader = null;

    // 构造函数中接收socket并且初始化BufferedReader
    public ServerThead(Socket socket)
            throws UnsupportedEncodingException, IOException {
        mSocket = socket;
        mBufferedReader = new BufferedReader(
                new InputStreamReader(mSocket.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        try {
            String content = null;

            //循环接收来自此客户端的消息
            //如果接收不到了，表面已经断开，就将此客户端从socketList中移除
            while ((content = mBufferedReader.readLine()) != null) {

                System.out.println(content);

                //向连接中的每个客户端发送数据
                //如果异常，说明断开，就将该条目从socketList中移除
                for (Iterator<Socket> it = SimpleServer.socketList.iterator();
                     it.hasNext();) {
                    Socket s = it.next();
                    try {
                        OutputStream os = s.getOutputStream();
                        os.write((content + "\n").getBytes("utf-8"));
                    } catch (SocketException e) {
                        e.printStackTrace();
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            SimpleServer.socketList.remove(mSocket);
        }
    }

}
