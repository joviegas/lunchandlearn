package com.lunchanlearn.httpserver.sdk.client;


import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.http.SdkHttpRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class MyServiceRequest  {


    public static String someSDKRequest() throws URISyntaxException {


        URI uri = new URI("http://localhost:8001/test?=new");
        String requestBody = "Hello World!";
        InputStream inputStream = new ByteArrayInputStream(requestBody.getBytes());
        SdkHttpFullRequest httpRequest =  SdkHttpFullRequest.builder()
                .method(SdkHttpMethod.POST)
                .uri(uri)
                .contentStreamProvider(() -> new ByteArrayInputStream(requestBody.getBytes()))
                .appendHeader("Content-Type", "text/plain")
                .appendHeader("Content-Length", String.valueOf(requestBody.length()))
                .build();




        return "done";







    }


}
