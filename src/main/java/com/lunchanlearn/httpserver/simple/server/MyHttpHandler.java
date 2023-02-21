package com.lunchanlearn.httpserver.simple.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange)) {


            requestParamValue = handlePostRequest(httpExchange);



        }

        System.out.println(httpExchange.getRequestMethod() +" received " + requestParamValue);

        handleResponse(httpExchange, requestParamValue);
    }

    private String handlePostRequest(HttpExchange httpExchange) throws IOException {

        // read request body
        BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
        String requestBody = br.lines().collect(Collectors.joining());

        // do some processing with the request body
        System.out.println(requestBody); // print the request body to console

        // set response headers
        httpExchange.getResponseHeaders().set("Content-Type", "text/plain");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.sendResponseHeaders(201, 0);

        // set response body
        OutputStream os = httpExchange.getResponseBody();
        os.write("POST request received".getBytes());
        os.close();
        return requestBody;

    }


    private String handleGetRequest(HttpExchange httpExchange) {


        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }


    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {

        OutputStream outputStream = httpExchange.getResponseBody();
        // encode HTML content
        String htmlResponse = "<html>" +
                "<body>" +
                "<h1>" +
                "Hello WORLD" +
                requestParamValue +
                "</h1>" +
                "</body>" +
                "</html>"
                // encode HTML content
                ;
        // this line is a must
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
