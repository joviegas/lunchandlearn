package com.lunchanlearn.httpserver.simple.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {


    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);


        server.createContext("/test", new  MyHttpHandler());
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
        System.out.println(" Server started on port 8001");


    }
}
