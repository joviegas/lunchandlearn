package com.lunchanlearn.httpserver.sdk.client;


import software.amazon.awssdk.http.*;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.utils.IoUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class MyServiceRequest  {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String sdkRequest = someSDKRequest();
    }


    public static String someSDKRequest() throws URISyntaxException, IOException {


//        URI uri = new URI("http://localhost:8001/test?=new");
//        String requestBody = "Hello World!";
//        InputStream inputStream = new ByteArrayInputStream(requestBody.getBytes());
//        SdkHttpFullRequest httpRequest =  SdkHttpFullRequest.builder()
//                .method(SdkHttpMethod.POST)
//                .uri(uri)
//                .contentStreamProvider(() -> new ByteArrayInputStream(requestBody.getBytes()))
//                .appendHeader("Content-Type", "text/plain")
//                .appendHeader("Content-Length", String.valueOf(requestBody.length()))
//                .build();


        SdkHttpRequest sdkRequest = SdkHttpRequest.builder()
                .uri(URI.create("http://localhost:8001/test?=new"))
                .method(SdkHttpMethod.GET)
                .build();
        HttpExecuteRequest request = HttpExecuteRequest.builder()
                .request(sdkRequest)
                .build();

        SdkHttpClient sdkHttpClient = ApacheHttpClient.create();
        ExecutableHttpRequest executableHttpRequest = sdkHttpClient.prepareRequest(request);

        HttpExecuteResponse httpExecuteResponse = executableHttpRequest.call();

        if(httpExecuteResponse.responseBody().isPresent()){
            String actualResult = IoUtils.toUtf8String(httpExecuteResponse.responseBody().get());


            System.out.println("actualResult " +actualResult);
        }else {
            System.out.println("No response body present");
        }




        return "done";







    }


}
