package com.lunchanlearn.simple.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class PostHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if ("POST".equals(exchange.getRequestMethod())) {

            // read request body
            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            String requestBody = br.lines().collect(Collectors.joining());

            // do some processing with the request body
            System.out.println(requestBody); // print the request body to console

            // set response headers
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, 0);

            // set response body
            OutputStream os = exchange.getResponseBody();
            os.write("POST request received".getBytes());
            os.close();

        } else {

            // return 405 Method Not Allowed if not a POST request
            exchange.getResponseHeaders().set("Allow", "POST");
            exchange.sendResponseHeaders(405, -1);
        }
    }
}

