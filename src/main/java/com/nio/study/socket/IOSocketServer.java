package com.nio.study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Lingfeng on 2016/6/8.
 */
public class IOSocketServer {

    public static void oldIOServer() {
        ServerSocket serverSocket = null;
        InputStream in = null;

        try {
            serverSocket = new ServerSocket(8080);
            int receiveMessageSize;

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);

                in = clientSocket.getInputStream();
                while (-1 != (receiveMessageSize = in.read(receiveBuffer))) {
                    byte[] temp = new byte[receiveMessageSize];
                    System.arraycopy(receiveBuffer, 0, temp, 0, receiveMessageSize);
                    System.out.println(new String(temp));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != serverSocket) {
                    serverSocket.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        oldIOServer();
    }
}
