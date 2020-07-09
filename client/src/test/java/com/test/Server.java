package com.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket accept = serverSocket.accept();
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("xxxx".getBytes());
            outputStream.flush();
        }
    }
}
