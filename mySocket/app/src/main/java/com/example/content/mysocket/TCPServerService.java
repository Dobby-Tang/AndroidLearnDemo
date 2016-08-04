package com.example.content.mysocket;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private boolean mIsServiceDestoryed = false;
    private String[] mDefinedMessages = new String[] {
            "你好啊，哈哈",
            "请问你叫什么名字啊？",
            "今天北京的天气不错啊，shy",
            "你知道吗，我是可以同时和多个人聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差，不知道真假"
    };


    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable{

        @SuppressWarnings("resource")

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                //监听本定8688端口
                serverSocket = new ServerSocket(8688);
            }catch (IOException e){
                System.out.println("establish tcp server failed,port:8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed){
                try {
                    //接受客户端请求
                    final Socket client = serverSocket.accept();
                    System.out.print("accept");
                    new Thread(){
                        @Override
                        public void run(){
                            try {
                                responseClient(client);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        };
                    }.start();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }



    private void responseClient(Socket client) throws IOException{
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(
                client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室");
        while (!mIsServiceDestoryed){
            String str = in.readLine();
            System.out.println("msg from client: " + str);
            if (str == null){
                break;
            }

            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send :" + msg);
        }


        System.out.print("client quit.");
        in.close();
        out.close();
        client.close();
    }










}
